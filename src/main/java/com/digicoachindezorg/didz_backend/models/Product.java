package com.digicoachindezorg.didz_backend.models;

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
    @OneToMany(mappedBy = "product")
    private List<Review> reviews; //Alles met een List doen in plaats van een ArrayList
    @Enumerated
    private ProductType productType;
    @OneToOne(mappedBy = "product")
    private StudyGroup studyGroup;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

}


    /*
   private List<Byte> images;
   Dit is voor images, maar maak eerst de rest van de applicatie.

   */
