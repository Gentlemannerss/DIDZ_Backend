package com.digicoachindezorg.didz_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long contactFormId;
    private String companyName;
    private String name;
    private Integer phoneNumber;
    private String eMail;
    private String description;
    private Boolean termsOfCondition;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id") // Add join column
    private User user;
}
