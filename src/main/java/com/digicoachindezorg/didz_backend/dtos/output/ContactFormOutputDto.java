package com.digicoachindezorg.didz_backend.dtos.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactFormOutputDto {
    public Long contactFormId;
    public String companyName;
    public String name;
    public Integer phoneNumber;
    public String eMail;
    public String description;
    public Boolean termsOfCondition;
}