package com.digicoachindezorg.didz_backend.dtos.output;

import com.digicoachindezorg.didz_backend.models.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserOutputDto {
    private Long id;
    private String username;
    private String fullName;
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
}
