package com.digicoachindezorg.didz_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String eMail;
    private LocalDate dateOfBirth;
    private String address;
    private String authority;
    private LocalDate availability;
    private String companyName;
    private Integer phoneNumber;
    private List<StudyGroupDto> studyGroups;
    private ReviewDto reviews;
    private List<MessageDto> messages;
    private List<ContactFormDto> contactForms;
    private List<InvoiceDto> invoices;
}
