package com.digicoachindezorg.didz_backend.dtos;

import com.digicoachindezorg.didz_backend.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {
    private String messageId;
    private Boolean isConcept;
    private String message;
    private User user;
}
