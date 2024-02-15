package dev.rajat.UserService.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rajat.UserService.DTOs.JwtDTO;
import dev.rajat.UserService.DTOs.UserDto;
import dev.rajat.UserService.Exceptions.AlreadyExistsException;
import dev.rajat.UserService.Exceptions.NotFoundException;
import dev.rajat.UserService.Exceptions.WrongPasswordException;
import dev.rajat.UserService.Models.Role;
import dev.rajat.UserService.Models.Session;
import dev.rajat.UserService.Models.SessionStatus;
import dev.rajat.UserService.Models.User;
import dev.rajat.UserService.Repositories.SessionRepository;
import dev.rajat.UserService.Repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SecretKey secretKey;
    private ObjectMapper objectMapper;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       ObjectMapper objectMapper){
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        this.objectMapper = objectMapper;
        this.secretKey = Jwts.SIG.HS256.key().build();
    }

    public UserDto signUp(String email, String password) throws AlreadyExistsException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isPresent()){
            throw new AlreadyExistsException("User email is already registered");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);
    }

    public ResponseEntity<UserDto> login(String email, String password) throws NotFoundException, WrongPasswordException, JsonProcessingException {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if(userOptional.isEmpty()){
            throw new NotFoundException("User is not found, please  sign up!");
        }
        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new WrongPasswordException();
        }
//        String token = RandomStringUtils.randomAlphanumeric(30);
        Map<String, Object> jwtData = new HashMap<>();
        jwtData.put("email", email);
//        String claimString  = objectMapper.writeValueAsString(user.getRoles());
//        jwtData.put("roles", user.getRoles());

        String token = Jwts.builder()
                .claims(jwtData).signWith(secretKey)
                .compact();
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

    public JwtDTO validate(String token, Long userId) throws NotFoundException, JsonProcessingException {
        Optional<Session> optionalSession = sessionRepository
                .findSessionByTokenAndUser_Id(token, userId);
        JwtDTO jwtDTO = new JwtDTO();
        if(optionalSession.isEmpty()){
            throw new NotFoundException("Session not found!");
        }
        Session session = optionalSession.get();
        if(session.getSessionStatus().equals(SessionStatus.ENDED)){
            jwtDTO.setSessionStatus(SessionStatus.ENDED);
        }
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);

        String email =(String)claimsJws.getPayload().get("email");
        String roles = (String)claimsJws.getPayload().get("roles");

        jwtDTO.setRoles(roles);
        jwtDTO.setEmail(email);
        if(!jwtDTO.getSessionStatus().equals(SessionStatus.ENDED)){
            jwtDTO.setSessionStatus(SessionStatus.ACTIVE);
        }
        return jwtDTO;
    }
}
