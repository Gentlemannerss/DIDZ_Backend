package com.digicoachindezorg.didz_backend.dtos.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceWithNewUserInputDto {
    public UserInputDto user;
    public InvoiceInputDto invoice;
}