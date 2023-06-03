package com.digicoachindezorg.didz_backend.dtos.output;

import com.digicoachindezorg.didz_backend.models.Message;
import com.digicoachindezorg.didz_backend.models.StudyGroup;
import com.digicoachindezorg.didz_backend.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageOutputDto {
    private Long messageId;
    private Boolean isConcept;
    private Message parentMessage;
    private User user;
    private StudyGroup studyGroup;
    private String receiverEmail;
}