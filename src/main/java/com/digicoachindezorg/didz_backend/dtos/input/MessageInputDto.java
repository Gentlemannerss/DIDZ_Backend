package com.digicoachindezorg.didz_backend.dtos.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageInputDto {
    private String messageContent;
    private Boolean isConcept;
    private Long senderId;
    private Long receiverId;
    private Long studyGroupId;
    @Email
    private String receiverEmail;
}
