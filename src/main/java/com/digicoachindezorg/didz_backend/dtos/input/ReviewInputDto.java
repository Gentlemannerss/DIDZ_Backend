package com.digicoachindezorg.didz_backend.dtos.input;

import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReviewInputDto {
    @NotNull(message = "Score cannot be null")
    private Integer score;

    private LocalDate dateOfWriting;

    @NotBlank(message = "Review description cannot be blank")
    private String reviewDescription;

    @NotNull(message = "Customer cannot be null")
    private User customer;

    @NotNull(message = "Product cannot be null")
    private Product product;
}