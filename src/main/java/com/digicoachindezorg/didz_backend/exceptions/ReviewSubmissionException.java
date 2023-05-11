package com.digicoachindezorg.didz_backend.exceptions;

public class ReviewSubmissionException extends RuntimeException {
    public ReviewSubmissionException() {
        super();
    }

    public ReviewSubmissionException(String message) {
        super(message);
    }
}
