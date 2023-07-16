package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.input.ContactFormInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.ContactFormOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.ContactForm;
import com.digicoachindezorg.didz_backend.repositories.ContactFormRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactFormService {

    private final ContactFormRepository contactFormRepository;

    public ContactFormService(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

    public List<ContactFormOutputDto> getAllContactForms() {
        List<ContactForm> contactForms = contactFormRepository.findAll();
        return contactForms.stream()
                .map(this::transferContactFormToOutputDto)
                .collect(Collectors.toList());
    }

    public ContactFormOutputDto getContactForm(Long id) throws RecordNotFoundException {
        ContactForm contactForm = contactFormRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Contact Form not found with id: " + id));
        return transferContactFormToOutputDto(contactForm);
    }

    public ContactFormOutputDto createContactForm(ContactFormInputDto contactFormDto) {
        ContactForm contactForm = transferInputDtoToContactForm(contactFormDto);
        ContactForm createdContactForm = contactFormRepository.save(contactForm);
        return transferContactFormToOutputDto(createdContactForm);
    }

    public ContactFormOutputDto updateContactForm(Long id, ContactFormInputDto contactFormDtoToUpdate) throws RecordNotFoundException {
        ContactForm existingContactForm = contactFormRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Contact Form not found with id: " + id));

        // Update the fields of the existing contact form
        ContactForm updatedContactForm = updateInputDtoToContactForm(contactFormDtoToUpdate,existingContactForm);

        ContactForm savedProduct = contactFormRepository.save(updatedContactForm);
        return transferContactFormToOutputDto(savedProduct);
    }

    public void deleteContactForm(Long id) throws RecordNotFoundException {
        if (!contactFormRepository.existsById(id)) {
            throw new RecordNotFoundException("Contact Form not found with id: " + id);
        }
        contactFormRepository.deleteById(id);
    }

    private ContactFormOutputDto transferContactFormToOutputDto(ContactForm contactForm) {
        ContactFormOutputDto outputDto = new ContactFormOutputDto();
        outputDto.setContactFormId(contactForm.getContactFormId());
        outputDto.setName(contactForm.getName());
        outputDto.setEMail(contactForm.getEMail());
        outputDto.setPhoneNumber(contactForm.getPhoneNumber());
        outputDto.setCompanyName(contactForm.getCompanyName());
        outputDto.setDescription(contactForm.getDescription());
        outputDto.setTermsOfCondition(contactForm.getTermsOfCondition());
        return outputDto;
    }

    private ContactForm transferInputDtoToContactForm(ContactFormInputDto inputDto) {
        ContactForm contactForm = new ContactForm();
        if (inputDto.getName()!=null) {
            contactForm.setName(inputDto.getName());
        }
        if (inputDto.getEMail()!=null) {
            contactForm.setEMail(inputDto.getEMail());
        }
        if (inputDto.getPhoneNumber()!=null) {
            contactForm.setPhoneNumber(inputDto.getPhoneNumber());
        }
        if (inputDto.getCompanyName()!=null) {
            contactForm.setCompanyName(inputDto.getCompanyName());
        }
        if (inputDto.getDescription()!=null) {
            contactForm.setDescription(inputDto.getDescription());
        }
        if (inputDto.getTermsOfCondition()!=null) {
            contactForm.setTermsOfCondition(inputDto.getTermsOfCondition());
        }
        return contactForm;
    }

    private ContactForm updateInputDtoToContactForm(ContactFormInputDto inputDto, ContactForm contactForm) {
        if (inputDto.getName()!=null) {
            contactForm.setName(inputDto.getName());
        }
        if (inputDto.getEMail()!=null) {
            contactForm.setEMail(inputDto.getEMail());
        }
        if (inputDto.getPhoneNumber()!=null) {
            contactForm.setPhoneNumber(inputDto.getPhoneNumber());
        }
        if (inputDto.getCompanyName()!=null) {
            contactForm.setCompanyName(inputDto.getCompanyName());
        }
        if (inputDto.getDescription()!=null) {
            contactForm.setDescription(inputDto.getDescription());
        }
        if (inputDto.getTermsOfCondition()!=null) {
            contactForm.setTermsOfCondition(inputDto.getTermsOfCondition());
        }
        return contactForm;
    }
}