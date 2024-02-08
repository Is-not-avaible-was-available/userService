package dev.rajat.UserService.Controllers;

import dev.rajat.UserService.DTOs.*;
import dev.rajat.UserService.Exceptions.AlreadyExistsException;
import dev.rajat.UserService.Exceptions.NotFoundException;
import dev.rajat.UserService.Exceptions.WrongPasswordException;
import dev.rajat.UserService.Models.SessionStatus;
import dev.rajat.UserService.Services.AuthService;
import dev.rajat.UserService.Services.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto requestDto) throws AlreadyExistsException {
        UserDto userDto = authService.signUp(requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDTO requestDTO) throws NotFoundException, WrongPasswordException {

        return authService.login(requestDTO.getEmail(), requestDTO.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogOutRequestDTO requestDTO){
        authService.logout(requestDTO.getToken(), requestDTO.getUserId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validate(@RequestBody ValidateTokenRequestDTO validateTokenRequestDTO){
       SessionStatus sessionStatus = authService
               .validate(validateTokenRequestDTO.getToken(),
                       validateTokenRequestDTO.getUserId());
        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }
}
