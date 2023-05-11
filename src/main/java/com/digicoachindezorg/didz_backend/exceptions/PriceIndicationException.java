package com.digicoachindezorg.didz_backend.exceptions;

public class PriceIndicationException extends RuntimeException {
    public PriceIndicationException() {
        super();
    }

    public PriceIndicationException(String message) {
        super(message);
    }
}