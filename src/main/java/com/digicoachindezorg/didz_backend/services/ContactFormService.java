package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.ContactFormDto;
import com.digicoachindezorg.didz_backend.exceptions.AlreadyExistsException;
import com.digicoachindezorg.didz_backend.exceptions.NotFoundException;
import com.digicoachindezorg.didz_backend.models.ContactForm;
import com.digicoachindezorg.didz_backend.repositories.ContactFormRepository;
import org.springframework.beans.BeanUtils;
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

    public ContactForm getContactForm(Long id) {
        return contactFormRepository.findById(id).orElseThrow(() -> new NotFoundException("Contact form not found with id " + id));
    }

    public ContactForm updateContactForm(Long id, ContactForm contactFormToUpdate) throws NotFoundException {
        ContactForm existingContactForm = contactFormRepository.findById(id).orElseThrow(() -> new NotFoundException("Contact form not found with id " + id));
        BeanUtils.copyProperties(contactFormToUpdate, existingContactForm);
        return contactFormRepository.save(existingContactForm);
    }

    public void deleteContactForm(Long id) throws NotFoundException {
        ContactForm contactForm = contactFormRepository.findById(id).orElseThrow(() -> new NotFoundException("Contact form not found with id "+ id));
        contactFormRepository.delete(contactForm);
    }

    public ContactForm createContactForm(ContactForm contactForm) {
        if (contactFormRepository.findById(contactForm.getContactFormId()).isPresent()) {
            throw new AlreadyExistsException("Contact form with " + contactForm.getContactFormId() + " already exists");
        }
        return contactFormRepository.save(contactForm);
    }

    //Bean Utils Way:
    /*public ContactFormDto transferModelToOutputDto(ContactForm contactForm) {
        ContactFormDto contactFormDto = new ContactFormDto();
        BeanUtils.copyProperties(contactForm, contactFormDto);
        return contactFormDto;
    }

    public ContactForm transferInputDtoToModel(ContactFormDto contactFormDto) {
        ContactForm contactForm = new ContactForm();
        BeanUtils.copyProperties(contactFormDto, contactForm, "id");
        return contactForm;
    }

    public ContactForm createContactForm(ContactForm contactForm) {
        return transferModelToOutputDto(contactFormRepository.save(transferInputDtoToModel(ContactFormDto)));
    }*/

    /*public ContactForm createContactForm(ContactForm contactFormDto) {
        ContactForm contactForm = new ContactForm();
        contactForm.setName(contactFormDto.name);
        contactForm.setCompanyName(contactFormDto.companyName);
        contactForm.setEMail(contactFormDto.eMail);
        contactForm.setPhoneNumber(contactFormDto.phoneNumber);
        contactForm.setDescription(contactFormDto.description);
        contactForm.setTermsOfCondition(contactFormDto.termsOfCondition);

        contactFormRepository.save(contactForm);

        return contactForm.getContactFormId();
    }*/
}
