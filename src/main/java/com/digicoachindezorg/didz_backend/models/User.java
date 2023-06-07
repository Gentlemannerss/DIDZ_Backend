package com.digicoachindezorg.didz_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String fullName;
    @Email
    private String eMail;
    private LocalDate dateOfBirth;
    private String address;
    private String authority;
    private LocalDate availability;
    private String companyName;
    private Integer phoneNumber;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<StudyGroup> studyGroups;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Review reviews;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<Message> messages;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ContactForm> contactForms;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Invoice> invoices;
}