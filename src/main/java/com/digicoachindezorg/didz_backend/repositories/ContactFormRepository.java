package com.digicoachindezorg.didz_backend.repositories;

import com.digicoachindezorg.didz_backend.models.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactFormRepository extends JpaRepository<ContactForm, Long> {
    List<ContactForm> getAllContactForms();

    // Maak hierin een
}