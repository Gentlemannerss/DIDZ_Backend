package com.digicoachindezorg.didz_backend.dtos;

import com.digicoachindezorg.didz_backend.models.*;

import java.time.LocalDate;
import java.util.List;

public class UserDto {
    private Long id;
    public String username;
    public String password;
    public String fullName;
    public String eMail;
    public LocalDate dateOfBirth;
    public String address;
    public String authority;
    public LocalDate availability;
    public String companyName;
    public Integer phoneNumber;
    public List<StudyGroup> studyGroups;
    public Review reviews;
    public List<Message> messages;
    public List<Invoice> invoices;
    public List<Byte> images;
    public List<ContactForm> contactForms;
}
