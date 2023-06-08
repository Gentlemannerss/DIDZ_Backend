package com.digicoachindezorg.didz_backend.dtos.input;

import com.digicoachindezorg.didz_backend.models.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductInputDto {
    @NotBlank
    private String productName;
    private Double price;
    private List<Long> reviewIds;
    @NotNull
    private ProductType productType;
    private Long studyGroupId;
    private Long invoiceId;
}
