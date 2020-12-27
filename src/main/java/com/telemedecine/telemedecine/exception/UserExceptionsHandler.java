package com.telemedecine.telemedecine.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class UserExceptionsHandler {

    private final Logger log = LoggerFactory.getLogger(UserExceptionsHandler.class);


    @ExceptionHandler({UserException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomError> handleException(UserException ex, HttpServletRequest request) {

        CustomError customError = new CustomError();
        customError.setErrorMessage("Un compte avec les memes identifiants existe deja !");

        return new ResponseEntity<CustomError>(customError, HttpStatus.BAD_REQUEST);
    }
}
