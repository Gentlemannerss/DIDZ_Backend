package com.digicoachindezorg.didz_backend.dtos;

import com.digicoachindezorg.didz_backend.models.Message;
import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.User;

import java.util.List;

public class StudyGroupDto {
    private String groupId;
    public Product product;
    public List<User> users;
    public List<Message> pinboardMessages;
}
