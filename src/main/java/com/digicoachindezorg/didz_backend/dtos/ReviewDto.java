package com.digicoachindezorg.didz_backend.dtos;

import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.User;

import java.time.LocalDate;

public class ReviewDto {
    private Integer reviewId;
    public Integer score;
    public LocalDate dateOfWriting;
    public String reviewDescription;
    public User customer;
    public Product product;
}
