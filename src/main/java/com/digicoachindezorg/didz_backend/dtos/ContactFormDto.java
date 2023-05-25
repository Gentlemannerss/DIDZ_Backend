package com.digicoachindezorg.didz_backend.dtos;

import com.digicoachindezorg.didz_backend.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ContactFormDto {
    public Integer contactFormId;
    public String companyName;
    @NotBlank
    public String name;
    public Integer phoneNumber;
    @Email
    public String eMail;
    public String description;
    public Boolean termsOfCondition;
    public User user;
}
 