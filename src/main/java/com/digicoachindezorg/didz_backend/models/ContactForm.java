package com.digicoachindezorg.didz_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Contact Forms")
public class ContactForm {
    @Id
    @GeneratedValue
    private Long id;
}