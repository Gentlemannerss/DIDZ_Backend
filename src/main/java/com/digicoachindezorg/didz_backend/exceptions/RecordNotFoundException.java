package com.digicoachindezorg.didz_backend.exceptions;

public class RecordNotFoundException extends RuntimeException{ //Je hebt de extend nodig, let goed op welke scope je hier aangeeft
    public RecordNotFoundException() {
    super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
