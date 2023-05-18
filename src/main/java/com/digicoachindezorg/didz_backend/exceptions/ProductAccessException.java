package com.digicoachindezorg.didz_backend.exceptions;

public class ProductAccessException extends RuntimeException {
    public ProductAccessException() {
        super();
    }

    public ProductAccessException(String message) {
        super(message);
    }
}
