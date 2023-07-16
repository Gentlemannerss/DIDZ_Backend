package com.digicoachindezorg.didz_backend.dtos.output;

import com.digicoachindezorg.didz_backend.models.Message;
import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudyGroupOutputDto {
    private String groupName;
    private Long groupId;
    private Product product;
    private List<User> users;
    private List<Message> pinboardMessages;
}