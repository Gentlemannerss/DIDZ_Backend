package com.digicoachindezorg.didz_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    private String productId;
    private String productName;
    private Double price;
    @OneToMany
    private List<Review> reviews; //Alles met een List doen in plaats van een ArrayList
    @Enumerated
    private ProductType productType;
    @OneToOne
    private StudyGroup studyGroup;
    @ManyToOne
    private Invoice invoice;

    private List<Byte> images; //dit zou moeten werken voor images
}
