package com.digicoachindezorg.didz_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue
    private Long productId;
    private String productName;
    private Double price;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Review> reviews;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @OneToOne(mappedBy = "product")
    @JsonIgnore
    private StudyGroup studyGroup;
    @ManyToMany(mappedBy = "products")
    private List<Invoice> invoices;
}

//todo maak een byte voor image, en maak een productDescription!