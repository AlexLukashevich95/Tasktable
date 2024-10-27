package com.alex.tasktable.controller;

import com.alex.tasktable.dto.ResponseDto;
import com.alex.tasktable.exceptions.BadRequestException;
import com.alex.tasktable.exceptions.InternalServerErrorException;
import com.alex.tasktable.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleBadRequestException(BadRequestException ex) {
        ex.printStackTrace();
        return new ResponseDto(HttpStatus.BAD_REQUEST.value(),"Incorrect request or input data",ex.toString());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto handleGeneralException(InternalServerErrorException ex) {
        ex.printStackTrace();
        return new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Error on server was occured",ex.toString());
    }
}
