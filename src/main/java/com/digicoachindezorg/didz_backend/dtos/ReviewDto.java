package com.digicoachindezorg.didz_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {
    private Integer reviewId;
    private Integer score;
    private LocalDate dateOfWriting;
    private String reviewDescription;
    private UserDto customer; // Assuming UserDto is the DTO class for User
    private ProductDto product; // Assuming ProductDto is the DTO class for Product
}
