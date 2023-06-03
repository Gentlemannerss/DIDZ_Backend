package com.digicoachindezorg.didz_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Invoices")
public class Invoice {
    @Id
    @GeneratedValue
    private Long invoiceId;
    private LocalDate orderDate;
    private Double totalPrice;
    private String address;
    private Double travelCost;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "invoice")
    private List<Product> products;
    private Integer amountOfParticipants;
    private String invoiceAddress;
    private Integer frequency;
    private String comments;
    private Boolean termsOfCondition;

}
