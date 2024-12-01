package com.abcbank.exception;

public class UserAuthException extends RuntimeException{
    public UserAuthException(String message) {
        super(message);
    }
}
