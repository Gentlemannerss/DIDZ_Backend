package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.ContactFormDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.services.ContactFormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ContactForm")
public class ContactFormController {

    private final ContactFormService contactFormService;

    public ContactFormController(ContactFormService contactFormService) {
        this.contactFormService = contactFormService;
    }

    @GetMapping
    public ResponseEntity<List<ContactFormDto>> getAllContactForms() {
        List<ContactFormDto> contactForms = contactFormService.getAllContactForms();
        return ResponseEntity.ok(contactForms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactFormDto> getContactForm(@PathVariable Long id) {
        ContactFormDto contactForm = contactFormService.getContactForm(id);
        return ResponseEntity.ok(contactForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactFormDto> updateContactForm(@PathVariable Long id, @RequestBody ContactFormDto contactFormDtoToUpdate) throws RecordNotFoundException {
        ContactFormDto updatedContactForm = contactFormService.updateContactForm(id, contactFormDtoToUpdate);
        return ResponseEntity.ok(updatedContactForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactForm(@PathVariable Long id) throws RecordNotFoundException {
        contactFormService.deleteContactForm(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<ContactFormDto> createContactForm(@RequestBody ContactFormDto contactFormDto) {
        ContactFormDto createdContactForm = contactFormService.createContactForm(contactFormDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContactForm);
    }
}

/* Versie met Paul:
package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.ContactFormDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.ContactForm;
import com.digicoachindezorg.didz_backend.services.ContactFormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ContactForm")
public class ContactFormController {

    // Dit is de Variable!
    private final ContactFormService contactFormService;
    // Dit is de Constructor!
    public ContactFormController(ContactFormService contactFormService) {
        this.contactFormService = contactFormService;
    }



    @GetMapping
    public ResponseEntity<List<ContactForm>> getAllContactForms() {
        return ResponseEntity.ok().body(contactFormService.getAllContactForms());
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

    @PostMapping("")
    public ResponseEntity<ContactFormDto> createContactForm(@RequestBody ContactFormDto contactFormDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactFormService.createContactForm(contactFormDto));
    }
}
*/
