package com.digicoachindezorg.didz_backend.dtos;

import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewDto {
    private Integer reviewId;
    private Integer score;
    private LocalDate dateOfWriting;
    private String reviewDescription;
    private User customer;
    private Product product;
}
