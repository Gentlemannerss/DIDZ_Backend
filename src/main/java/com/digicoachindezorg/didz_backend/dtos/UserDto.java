package com.digicoachindezorg.didz_backend.dtos;

import com.digicoachindezorg.didz_backend.models.Invoice;
import com.digicoachindezorg.didz_backend.models.Message;
import com.digicoachindezorg.didz_backend.models.Review;
import com.digicoachindezorg.didz_backend.models.StudyGroup;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
    private List<StudyGroup> studyGroups;
    private Review reviews;
    private List<Message> messages;
    private List<Invoice> invoices;
    private List<Byte> images;
}
