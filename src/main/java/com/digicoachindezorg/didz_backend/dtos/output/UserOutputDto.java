package com.digicoachindezorg.didz_backend.dtos.output;

import com.digicoachindezorg.didz_backend.models.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserOutputDto {
    private Long id;
    private String username;
    private String fullName;
    private String password;
    private String eMail;
    private LocalDate dateOfBirth;
    private String address;
    private String authority;
    private LocalDate availability;
    private String companyName;
    private Integer phoneNumber;
    private List<StudyGroup> studyGroups;
    private Review reviews;
    private List<Message> messages;
    private List<ContactForm> contactForms;
    private List<Invoice> invoices;
    private Boolean enabled;
    private String apikey;
    @JsonSerialize
    private Set<Authority> authorities;

}
