package com.digicoachindezorg.didz_backend.dtos.output;

import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReviewOutputDto {
    private Long reviewId;
    private Integer score;
    private LocalDate dateOfWriting;
    private String reviewDescription;
    private User customer; //Kan UserDto zijn, maar dit fck met je code.
    private Product product;
}
