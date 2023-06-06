package com.digicoachindezorg.didz_backend.dtos.input;

import com.digicoachindezorg.didz_backend.models.Message;
import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudyGroupInputDto {
    private String groupName;
    @Valid
    private Product product;

    @NotNull(message = "Users list cannot be null")
    private List<User> users;

    private List<Message> pinboardMessages;
}
