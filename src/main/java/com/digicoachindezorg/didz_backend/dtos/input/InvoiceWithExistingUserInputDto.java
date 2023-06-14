package com.digicoachindezorg.didz_backend.dtos.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceWithExistingUserInputDto {
    public Long userId;
    public InvoiceInputDto invoice;
}