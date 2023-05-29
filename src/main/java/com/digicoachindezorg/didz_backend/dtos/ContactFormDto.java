package com.digicoachindezorg.didz_backend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactFormDto {
    public Integer contactFormId;
    public String companyName;
    @NotBlank
    public String name;
    public Integer phoneNumber;
    public String eMail;
    public String description;
    public Boolean termsOfCondition;
    public List<ContactFormDto> contactForms; // Added contactForms field
}
 