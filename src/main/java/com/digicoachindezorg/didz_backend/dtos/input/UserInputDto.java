package com.digicoachindezorg.didz_backend.dtos.input;

import com.digicoachindezorg.didz_backend.models.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserInputDto {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    private String eMail;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Authority is required")
    private String authority;

    private LocalDate availability;

    private String companyName;

    @NotNull(message = "Phone number is required")
    private Integer phoneNumber;

    private List<StudyGroup> studyGroups;

    private Review reviews;

    private List<Message> messages;

    private List<ContactForm> contactForms;

    private List<Invoice> invoices;

    private Boolean enabled;
    @JsonSerialize
    private Set<Authority> authorities;
}

