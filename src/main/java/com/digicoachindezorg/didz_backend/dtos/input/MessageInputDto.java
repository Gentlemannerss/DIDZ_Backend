package com.digicoachindezorg.didz_backend.dtos.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageInputDto {
    @NotNull
    private String messageContent;
    private Boolean isConcept;
    @NotNull
    private Long senderId;
    private Long studyGroupId;
    @Email
    private String receiverEmail;
}
