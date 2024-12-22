package com.alex.tasktable.controller;

import com.alex.tasktable.dto.ResponseDto;
import com.alex.tasktable.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.web.bind.annotation.ControllerAdvice(annotations = RestController.class)
public class ExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto handleResourceNotFoundException(ResourceNotFoundException ex) {
        ex.printStackTrace();
        return new ResponseDto(HttpStatus.NOT_FOUND.value(),"Resource not found",ex.toString());
    }

}
