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
    private String privateEMail;
    private String workEMail;
    private LocalDate dateOfBirth;
    private String address;
    private String authority;
    private LocalDate availability;
    private String companyName;
    private String companyAddress;
    private String gender;
    private String phoneNumber;
    private List<StudyGroup> studyGroups;
    private List<Review> reviews;
    private List<Message> messages;
    private List<ContactForm> contactForms;
    private List<Invoice> invoices;
    private Boolean enabled;
    private String apikey;
    private String profilePicUrl;
    private String fileName;
    @JsonSerialize
    private Set<Authority> authorities;

}
