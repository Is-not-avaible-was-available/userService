package dev.rajat.UserService.Services;

import dev.rajat.UserService.DTOs.UserDto;
import dev.rajat.UserService.Exceptions.AlreadyExistsException;
import dev.rajat.UserService.Exceptions.NotFoundException;
import dev.rajat.UserService.Exceptions.WrongPasswordException;
import dev.rajat.UserService.Models.Session;
import dev.rajat.UserService.Models.SessionStatus;
import dev.rajat.UserService.Models.User;
import dev.rajat.UserService.Repositories.SessionRepository;
import dev.rajat.UserService.Repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    public UserDto signUp(String email, String password) throws AlreadyExistsException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isPresent()){
            throw new AlreadyExistsException("User email is already registered");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);
    }

    public ResponseEntity<UserDto> login(String email, String password) throws NotFoundException, WrongPasswordException {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if(userOptional.isEmpty()){
            throw new NotFoundException("User is not found, please  sign up!");
        }
        User user = userOptional.get();
        if(!Objects.equals(user.getPassword(), password)){
            throw new WrongPasswordException();
        }
        String token = RandomStringUtils.randomAlphanumeric(30);
        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setUser(user);
        session.setToken(token);
        Session savedSession = sessionRepository.save(session);

        UserDto userDto = UserDto.from(user);
        MultiValueMap<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token" + token);
        return new ResponseEntity<>(userDto, headers, HttpStatus.OK);
    }

    public void logout(String token, Long id){
        Optional<Session> optionalSession = sessionRepository
                .findSessionByTokenAndUser_Id(token, id);
        if(optionalSession.isEmpty()){
            return;
        }

        Session session = optionalSession.get();
        session.setSessionStatus(SessionStatus.ENDED);

        sessionRepository.save(session);

    }

    public SessionStatus validate(String token, Long userId){
        Optional<Session> optionalSession = sessionRepository
                .findSessionByTokenAndUser_Id(token, userId);
        if(optionalSession.isEmpty()){
            return SessionStatus.ENDED;
        }
        Session session = optionalSession.get();
        if(session.getSessionStatus().equals(SessionStatus.ENDED)){
            return SessionStatus.ENDED;
        }
        return SessionStatus.ACTIVE;
    }
}
