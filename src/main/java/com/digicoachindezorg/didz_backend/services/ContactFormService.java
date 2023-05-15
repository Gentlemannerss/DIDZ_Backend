package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.repositories.ContactFormRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactFormService {

    private final ContactFormRepository contactFormRepository;

    public ContactFormService(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }
}
