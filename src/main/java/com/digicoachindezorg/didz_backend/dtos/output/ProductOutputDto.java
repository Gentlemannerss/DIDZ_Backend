package com.digicoachindezorg.didz_backend.dtos.output;
import com.digicoachindezorg.didz_backend.models.Invoice;
import com.digicoachindezorg.didz_backend.models.ProductType;
import com.digicoachindezorg.didz_backend.models.Review;
import com.digicoachindezorg.didz_backend.models.StudyGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductOutputDto {
    private Long productId;
    private String productName;
    private Double price;
    private List<Review> reviews;
    private ProductType productType;
    private StudyGroup studyGroup;
    private Invoice invoice;
}
