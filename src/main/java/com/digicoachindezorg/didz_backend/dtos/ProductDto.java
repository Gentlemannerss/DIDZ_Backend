package com.digicoachindezorg.didz_backend.dtos;

import com.digicoachindezorg.didz_backend.models.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private String productId;
    private String productName;
    private Double price;
    private List<ReviewDto> reviews;
    private ProductType productType;
    private StudyGroupDto studyGroup;
    private InvoiceDto invoice;
}
