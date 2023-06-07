package com.digicoachindezorg.didz_backend.dtos.input;

import com.digicoachindezorg.didz_backend.models.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class InvoiceInputDto {
    @NotNull
    @Size(min = 1, max = 100)
    public String address;

    /*@NotNull
    @Positive
    public Double travelCost;*/

    public List<Long> productsId;

    @NotNull
    @Positive
    public Integer amountOfParticipants;

    @NotNull
    @Size(min = 1, max = 100)
    public String invoiceAddress;

    public Integer frequency;

    public LocalDate orderDate;
    @NotNull
    @Positive
    public Double totalPrice;

    public Long userId;

    @Size(max = 200)
    public String comments;

    public Boolean termsOfCondition;
}