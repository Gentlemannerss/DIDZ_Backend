package com.digicoachindezorg.didz_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Contact Forms")
public class ContactForm {
    @Id
    @GeneratedValue
    private Integer contactFormId;
    private String companyName;
    private String name;
    private Integer phoneNumber;
    private String eMail;
    private String description;
    private Boolean termsOfCondition;
    /*@OneToMany(mappedBy = "contactForm") // Added relationship mapping
    private List<ContactForm> contactForms;*/
    @ManyToOne
    @JoinColumn(name = "user_id") // Add join column
    private User user;
}
