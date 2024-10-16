package com.alex.tasktable.exceptions;

public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException(String message, Exception e){
        super(message,e);
    }
}
