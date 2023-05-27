package com.digicoachindezorg.didz_backend.repositories;

import com.digicoachindezorg.didz_backend.models.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactFormRepository extends JpaRepository<ContactForm, Long> {

    // Maak hierin alleen specefieke zoekopdrachten zoals bijvoorbeeld zoek op kenteken.
}