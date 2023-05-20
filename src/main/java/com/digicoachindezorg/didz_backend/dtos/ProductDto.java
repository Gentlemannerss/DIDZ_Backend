package com.digicoachindezorg.didz_backend.dtos;

import com.digicoachindezorg.didz_backend.models.Invoice;
import com.digicoachindezorg.didz_backend.models.ProductType;
import com.digicoachindezorg.didz_backend.models.Review;
import com.digicoachindezorg.didz_backend.models.StudyGroup;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDto {
    private String productId;
    private String productName;
    private Double price;
    private List<Review> reviews;
    private ProductType productType;
    private StudyGroup studyGroup;
    private Invoice invoice;
    private List<Byte> images;
}
