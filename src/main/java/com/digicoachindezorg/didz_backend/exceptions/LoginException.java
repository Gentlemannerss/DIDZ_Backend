package com.digicoachindezorg.didz_backend.exceptions;

public class LoginException extends RuntimeException {
    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }
}
