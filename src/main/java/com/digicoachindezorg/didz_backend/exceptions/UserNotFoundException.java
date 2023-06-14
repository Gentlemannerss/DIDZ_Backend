package com.digicoachindezorg.didz_backend.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long userId) {
        super("Cannot find user " + userId);
    }

}