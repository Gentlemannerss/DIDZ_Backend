package com.digicoachindezorg.didz_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudyGroupDto {
    private String groupId;
    private ProductDto product;
    private List<UserDto> users;
    private List<MessageDto> pinboardMessages;
}
