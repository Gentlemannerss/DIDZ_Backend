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
    @PutMapping("/{contactFormId}")
    public ResponseEntity<ContactFormOutputDto> updateContactForm(@PathVariable Long contactFormId, @RequestBody ContactFormInputDto contactFormDtoToUpdate) throws RecordNotFoundException {
        ContactFormOutputDto updatedContactForm = contactFormService.updateContactForm(contactFormId, contactFormDtoToUpdate);
        return ResponseEntity.ok(updatedContactForm);
    }
    @DeleteMapping("/{contactFormId}")
    public ResponseEntity<Void> deleteContactForm(@PathVariable Long contactFormId) throws RecordNotFoundException {
        contactFormService.deleteContactForm(contactFormId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{contactFormId}")
    public ResponseEntity<ContactFormOutputDto> getContactForm(@PathVariable Long contactFormId) {
        ContactFormOutputDto contactForm = contactFormService.getContactForm(contactFormId);
        return ResponseEntity.ok(contactForm);
    }
    @GetMapping
    public ResponseEntity<List<ContactFormOutputDto>> getAllContactForms() {
        List<ContactFormOutputDto> contactForms = contactFormService.getAllContactForms();
        return ResponseEntity.ok(contactForms);
    }
}