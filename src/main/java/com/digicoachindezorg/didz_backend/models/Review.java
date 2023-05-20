package com.digicoachindezorg.didz_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.id.GUIDGenerator;

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
    private Integer reviewId;
    private Integer score;
    private LocalDate dateOfWriting;
    private String reviewDescription;
    @OneToOne
    private User customer; // Hier de rol van User<rol customer> opvangen
    @ManyToOne
    private Product product;
}
