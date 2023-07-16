package com.digicoachindezorg.didz_backend.dtos.input;

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
    private Long productId;

    @NotNull(message = "Users list cannot be null")
    private List<Long> userIds;

}
