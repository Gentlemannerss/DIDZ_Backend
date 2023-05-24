package com.digicoachindezorg.didz_backend.dtos;

import com.digicoachindezorg.didz_backend.models.Invoice;
import com.digicoachindezorg.didz_backend.models.ProductType;
import com.digicoachindezorg.didz_backend.models.Review;
import com.digicoachindezorg.didz_backend.models.StudyGroup;

import java.util.List;

public class ProductDto {
    private String productId;
    public String productName;
    public Double price;
    public List<Review> reviews;
    public ProductType productType;
    public StudyGroup studyGroup;
    public Invoice invoice;
    public List<Byte> images;
}
