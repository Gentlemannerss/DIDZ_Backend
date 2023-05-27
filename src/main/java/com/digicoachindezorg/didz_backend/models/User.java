package com.digicoachindezorg.didz_backend.models;

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
    private List<StudyGroup> studyGroups;
    @OneToOne //Moet eigenlijk gemapped worden door User
    private Review reviews;
    @OneToMany(mappedBy = "user")
    private List<Message> messages;
    @OneToMany(mappedBy = "user")
    private List<ContactForm> contactForms;
    @OneToMany(mappedBy = "user")
    private List<Invoice> invoices;

   /*
   private List<Byte> images;
   Dit is voor images, maar maak eerst de rest van de applicatie.

   */
}
