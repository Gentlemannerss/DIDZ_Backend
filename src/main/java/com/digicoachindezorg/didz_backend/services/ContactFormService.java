package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.models.ContactForm;
import com.digicoachindezorg.didz_backend.repositories.ContactFormRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactFormService {

    private final ContactFormRepository contactFormRepository;

    public ContactFormService(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

    public static List<ContactForm> getAllContactForms() {
        return contactFormRepository.getAllContactForms();
    }
}
