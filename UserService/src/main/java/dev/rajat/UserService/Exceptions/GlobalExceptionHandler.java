package dev.rajat.UserService.Exceptions;

import dev.rajat.UserService.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(new ExceptionDTO(
                notFoundException.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionDTO> handleAlreadyExistsException(AlreadyExistsException alreadyExistsException){
        return new ResponseEntity<>(new ExceptionDTO(
                alreadyExistsException.getMessage(), HttpStatus.CONFLICT),
                HttpStatus.CONFLICT);
    }
    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ExceptionDTO> handleWrongPasswordException(WrongPasswordException wrongPasswordException){
        return new ResponseEntity<>(new ExceptionDTO(
                "Wrong password", HttpStatus.CONFLICT),
                HttpStatus.CONFLICT);
    }
}
