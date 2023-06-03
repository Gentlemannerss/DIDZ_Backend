package com.digicoachindezorg.didz_backend.dtos.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactFormInputDto {
    @NotBlank
    public String companyName;

    @NotBlank
    public String name;

    public Integer phoneNumber;

    @NotBlank
    public String eMail;

    public String description;

    public Boolean termsOfCondition;
}
