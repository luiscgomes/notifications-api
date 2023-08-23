package com.notificationsapi.application.exceptionhandlers;

import com.notificationsapi.domain.exceptions.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ArrayList<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseBody
    public ArrayList<String> handleValidationExceptions(CategoryNotFoundException ex) {
        ArrayList<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return errors;
    }
}
