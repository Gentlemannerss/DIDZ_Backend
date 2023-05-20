package com.digicoachindezorg.didz_backend.dtos;

import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InvoiceDto {
    private Integer invoiceId;
    private LocalDate orderDate;
    private Double totalPrice;
    private String address;
    private Double travelCost;
    private User user;
    private Product product;
    private Integer amountOfParticipants;
    private String invoiceAddress;
    private Integer frequency;
    private String comments;
    private Boolean termsOfCondition;
}
