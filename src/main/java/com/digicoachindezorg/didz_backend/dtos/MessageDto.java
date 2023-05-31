package com.digicoachindezorg.didz_backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {
    private String messageId;
    private Boolean isConcept;
    private MessageDto parentMessage;
    private UserDto user;
    private StudyGroupDto studyGroup;
    private String receiverEmail;
}
