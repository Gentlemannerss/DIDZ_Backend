package com.digicoachindezorg.didz_backend.repositories;

import com.digicoachindezorg.didz_backend.models.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactFormRepository extends JpaRepository<ContactForm, Long> {}