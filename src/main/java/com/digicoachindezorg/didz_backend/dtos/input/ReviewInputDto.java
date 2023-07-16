package com.digicoachindezorg.didz_backend.dtos.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ReviewInputDto {
    @NotNull(message = "Score cannot be null")
    private Integer score;

    @NotBlank(message = "Review description cannot be blank")
    private String reviewDescription;

    @NotNull(message = "Customer cannot be null")
    private Long customerId;

    @NotNull(message = "Product cannot be null")
    private Long productId;
}