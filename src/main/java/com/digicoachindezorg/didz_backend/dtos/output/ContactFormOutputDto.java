package com.digicoachindezorg.didz_backend.dtos.output;

import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactFormOutputDto {
    public Long contactFormId;
    public String companyName;
    public String name;
    public Integer phoneNumber;
    public String eMail;
    public String description;
    public Boolean termsOfCondition;
}