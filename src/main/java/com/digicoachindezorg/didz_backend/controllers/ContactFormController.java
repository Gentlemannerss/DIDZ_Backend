package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.ContactForm;
import com.digicoachindezorg.didz_backend.services.ContactFormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ContactFormController {

    private final ContactFormService contactFormService;

    public ContactFormController(ContactFormService contactFormService) {
        this.contactFormService = contactFormService;
    }

    @GetMapping
    public ResponseEntity<List<ContactForm>> getAllContactForms() {
        return ResponseEntity.ok().body(ContactFormService.getAllContactForms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactForm> getContactForm(@PathVariable Long id) {
        return ResponseEntity.ok(contactFormService.getContactForm(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactForm> updateContactForm(@PathVariable Long id, @RequestBody ContactForm contactFormToUpdate) throws RecordNotFoundException {
        ContactForm updatedContactForm = contactFormService.updateContactForm(id, contactFormToUpdate);
        return ResponseEntity.ok(updatedContactForm);
    }

    @DeleteMapping("/{id}")
    public void deleteContactForm(@PathVariable Long id) throws RecordNotFoundException {
        contactFormService.deleteContactForm(id);
    }

    @PostMapping
    public ResponseEntity<ContactForm> createContactForm(@RequestBody ContactForm contactForm) {
        ContactForm createdContactForm = contactFormService.createContactForm(contactForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContactForm);
    }
}
