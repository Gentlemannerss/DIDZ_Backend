package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.ContactFormDto;
import com.digicoachindezorg.didz_backend.exceptions.NotFoundException;
import com.digicoachindezorg.didz_backend.models.ContactForm;
import com.digicoachindezorg.didz_backend.repositories.ContactFormRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public ContactFormDto getContactForm(Long id) {
        ContactForm contactForm = contactFormRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Contact form not found with id " + id));
        return toContactFormDto(contactForm);
    }

    public ContactFormDto updateContactForm(Long id, ContactFormDto contactFormDtoToUpdate) throws NotFoundException {
        ContactForm existingContactForm = contactFormRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Contact form not found with id " + id));

        ContactForm updatedContactForm = fromContactFormDto(contactFormDtoToUpdate);
        updatedContactForm.setContactFormId(existingContactForm.getContactFormId());

        ContactForm savedContactForm = contactFormRepository.save(updatedContactForm);
        return toContactFormDto(savedContactForm);
    }

    public void deleteContactForm(Long id) throws NotFoundException {
        Optional<ContactForm> contactFormOptional = contactFormRepository.findById(id);
        if (contactFormOptional.isPresent()) {
            ContactForm contactForm = contactFormOptional.get();
            contactFormRepository.delete(contactForm);
        } else {
            throw new NotFoundException("Contact form not found with id " + id);
        }
    }

    public ContactFormDto createContactForm(ContactFormDto contactFormDto) {
        ContactForm contactForm = fromContactFormDto(contactFormDto);
        ContactForm savedContactForm = contactFormRepository.save(contactForm);
        return toContactFormDto(savedContactForm);
    }

    private ContactFormDto toContactFormDto(ContactForm contactForm) {
        ContactFormDto contactFormDto = new ContactFormDto();
        BeanUtils.copyProperties(contactForm, contactFormDto, "contactFormId" /*Kunt hier nog een ignore meegeveven van bijvoorbeeld ID*/);
        return contactFormDto;
    }

    private ContactForm fromContactFormDto(ContactFormDto contactFormDto) {
        ContactForm contactForm = new ContactForm();
        BeanUtils.copyProperties(contactFormDto, contactForm);
        return contactForm;
    }
}

/* Versie met Paul:
package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.ContactFormDto;
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

    public List<ContactForm> getAllContactForms() {
        return contactFormRepository.findAll();
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

    /*public ContactForm createContactForm(ContactForm contactForm) {
        if (contactFormRepository.findById(contactForm.getContactFormId()).isPresent()) {
            throw new AlreadyExistsException("Contact form with " + contactForm.getContactFormId() + " already exists");
        }
        return contactFormRepository.save(contactForm);
    }


    public ContactFormDto createContactForm(ContactFormDto contactFormDto) {
        ContactForm contactForm = new ContactForm();
        contactForm.setName(contactFormDto.name);
        contactForm.setCompanyName(contactFormDto.companyName);
        contactForm.setEMail(contactFormDto.eMail);
        contactForm.setPhoneNumber(contactFormDto.phoneNumber);
        contactForm.setDescription(contactFormDto.description);
        contactForm.setTermsOfCondition(contactFormDto.termsOfCondition);

        ContactForm savedcontactform = contactFormRepository.save(contactForm);

        ContactFormDto contactFormDto1 = new ContactFormDto();
        contactFormDto1.setName(savedcontactform.getName());
        //Hier moet ik alle andere entitiy's een set (....get..) geven
        //Of mogelijk een EntityToDto aanroepen

        return contactFormDto1;
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
    }

    public ContactFormDto toContactFormDto() {
        ContactFormDto contactFormDto = new ContactFormDto();
        contactFormDto.setContactFormId(this.contactFormId);
        contactFormDto.setCompanyName(this.companyName);
        contactFormDto.setName(this.name);
        contactFormDto.setPhoneNumber(this.phoneNumber);
        contactFormDto.setEMail(this.eMail);
        contactFormDto.setDescription(this.description);
        contactFormDto.setTermsOfCondition(this.termsOfCondition);
        return contactFormDto;
    }

    public static ContactForm fromContactFormDto(ContactFormDto contactFormDto) {
        ContactForm contactForm = new ContactForm();
        contactForm.setContactFormId(contactFormDto.getContactFormId());
        contactForm.setCompanyName(contactFormDto.getCompanyName());
        contactForm.setName(contactFormDto.getName());
        contactForm.setPhoneNumber(contactFormDto.getPhoneNumber());
        contactForm.setEMail(contactFormDto.getEMail());
        contactForm.setDescription(contactFormDto.getDescription());
        contactForm.setTermsOfCondition(contactFormDto.getTermsOfCondition());
        return contactForm;
    }
}

*/
