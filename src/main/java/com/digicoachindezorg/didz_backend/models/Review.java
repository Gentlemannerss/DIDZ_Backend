package com.digicoachindezorg.didz_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Reviews")
public class Review {
    @Id
    @GeneratedValue
    private Long reviewId;
    private Integer score;
    private LocalDate dateOfWriting;
    private String reviewDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User customer;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Product product;
}
