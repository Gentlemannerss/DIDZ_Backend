package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.input.ContactFormInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.ContactFormOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.services.ContactFormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactform")
public class ContactFormController {

    private final ContactFormService contactFormService;

    public ContactFormController(ContactFormService contactFormService) {
        this.contactFormService = contactFormService;
    }
    @PostMapping("")
    public ResponseEntity<ContactFormOutputDto> createContactForm(@RequestBody ContactFormInputDto contactFormDto) {
        ContactFormOutputDto createdContactForm = contactFormService.createContactForm(contactFormDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContactForm);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ContactFormOutputDto> updateContactForm(@PathVariable Long id, @RequestBody ContactFormInputDto contactFormDtoToUpdate) throws RecordNotFoundException {
        ContactFormOutputDto updatedContactForm = contactFormService.updateContactForm(id, contactFormDtoToUpdate);
        return ResponseEntity.ok(updatedContactForm);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactForm(@PathVariable Long id) throws RecordNotFoundException {
        contactFormService.deleteContactForm(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContactFormOutputDto> getContactForm(@PathVariable Long id) {
        ContactFormOutputDto contactForm = contactFormService.getContactForm(id);
        return ResponseEntity.ok(contactForm);
    }
    @GetMapping
    public ResponseEntity<List<ContactFormOutputDto>> getAllContactForms() {
        List<ContactFormOutputDto> contactForms = contactFormService.getAllContactForms();
        return ResponseEntity.ok(contactForms);
    }
}

/*      TODO:
    -Check if possible to change RequestMapping above GetAllContactForms
    -Check if mapping above create is correct
    -Check for id (needs to be contactformID because of duplicate with user?)
*/