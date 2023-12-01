package com.ynov.security.exception;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(String message){
        super(message);
    }


}
