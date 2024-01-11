package com.scaler.ProductService1.Exceptions;

import com.scaler.ProductService1.DTOs.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.NotActiveException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.NOT_FOUND, notFoundException.getMessage())
                , HttpStatus.NOT_FOUND);
    }
}
