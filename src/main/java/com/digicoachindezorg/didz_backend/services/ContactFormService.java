package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.ContactFormDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.ContactForm;
import com.digicoachindezorg.didz_backend.repositories.ContactFormRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactFormService {

    private final ContactFormRepository contactFormRepository;

    public ContactFormService(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

    public List<ContactFormDto> getAllContactForms() {
        List<ContactForm> contactForms = contactFormRepository.findAll();
        return contactForms.stream()
                .map(this::toContactFormDto)
                .collect(Collectors.toList());
    }

    public ContactFormDto getContactForm(Long id) throws RecordNotFoundException {
        ContactForm contactForm = contactFormRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Contact Form not found with id: " + id));
        return toContactFormDto(contactForm);
    }

    public ContactFormDto createContactForm(ContactFormDto contactFormDto) {
        ContactForm contactForm = fromContactFormDto(contactFormDto);
        ContactForm createdContactForm = contactFormRepository.save(contactForm);
        return toContactFormDto(createdContactForm);
    }

    public ContactFormDto updateContactForm(Long id, ContactFormDto contactFormDtoToUpdate) throws RecordNotFoundException {
        ContactForm existingContactForm = contactFormRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Contact Form not found with id: " + id));

        // Update the fields of the existing contact form
        BeanUtils.copyProperties(contactFormDtoToUpdate, existingContactForm);

        ContactForm updatedContactForm = contactFormRepository.save(existingContactForm);
        return toContactFormDto(updatedContactForm);
    }

    public void deleteContactForm(Long id) throws RecordNotFoundException {
        if (!contactFormRepository.existsById(id)) {
            throw new RecordNotFoundException("Contact Form not found with id: " + id);
        }
        contactFormRepository.deleteById(id);
    }

    private ContactFormDto toContactFormDto(ContactForm contactForm) {
        ContactFormDto contactFormDto = new ContactFormDto();
        BeanUtils.copyProperties(contactForm, contactFormDto);
        return contactFormDto;
    }

    private ContactForm fromContactFormDto(ContactFormDto contactFormDto) {
        ContactForm contactForm = new ContactForm();
        BeanUtils.copyProperties(contactFormDto, contactForm);
        return contactForm;
    }
}