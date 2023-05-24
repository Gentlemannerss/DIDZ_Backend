package com.digicoachindezorg.didz_backend.dtos;

import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.User;

import java.time.LocalDate;

public class InvoiceDto {
    private Integer invoiceId;
    public LocalDate orderDate;
    public Double totalPrice;
    public String address;
    public Double travelCost;
    public User user;
    public Product product;
    public Integer amountOfParticipants;
    public String invoiceAddress;
    public Integer frequency;
    public String comments;
    public Boolean termsOfCondition;
}
