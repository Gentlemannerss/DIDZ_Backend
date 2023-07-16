package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.dtos.input.ContactFormInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.ContactFormOutputDto;
import com.digicoachindezorg.didz_backend.models.ContactForm;
import com.digicoachindezorg.didz_backend.models.User;
import com.digicoachindezorg.didz_backend.repositories.ContactFormRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactFormServiceTest {

    @Mock
    ContactFormRepository contactFormRepository;

    @InjectMocks
    ContactFormService contactFormService;

    @Test
    void getAllContactForms() {
        // Arrange
        ContactForm contactForm1 = new ContactForm();
        contactForm1.setContactFormId(1L);
        ContactForm contactForm2 = new ContactForm();
        contactForm2.setContactFormId(2L);
        List<ContactForm> contactForms = Arrays.asList(contactForm1, contactForm2);

        when(contactFormRepository.findAll()).thenReturn(contactForms);

        // Act
        List<ContactFormOutputDto> result = contactFormService.getAllContactForms();

        // Assert
        assertEquals(contactForms.size(), result.size());
        assertEquals(contactForm1.getContactFormId(), result.get(0).getContactFormId());
        assertEquals(contactForm2.getContactFormId(), result.get(1).getContactFormId());
    }

    @Test
    void getContactForm() {
        //Arrange
        ContactForm contactForm = new ContactForm();
        contactForm.setContactFormId(1L);
        contactForm.setCompanyName("testcompany");
        contactForm.setName("test");
        contactForm.setPhoneNumber(12345678);
        contactForm.setEMail("test@email.nl");
        contactForm.setDescription("testdescription");
        contactForm.setTermsOfCondition(true);
        contactForm.setUser(new User());

        when(contactFormRepository.findById(anyLong())).thenReturn(Optional.of(contactForm));
        //Act
        ContactFormOutputDto contactFormOutputDto = contactFormService.getContactForm(1L);

        //Assert
        assertEquals(contactFormOutputDto.getContactFormId(), contactForm.getContactFormId());
    }

    @Test
    void createContactForm() {
        // Arrange
        ContactFormInputDto contactFormInputDto = new ContactFormInputDto();
        contactFormInputDto.setCompanyName("testcompany");
        contactFormInputDto.setName("test");
        contactFormInputDto.setPhoneNumber(12345678);
        contactFormInputDto.setEMail("test@email.nl");
        contactFormInputDto.setDescription("testdescription");
        contactFormInputDto.setTermsOfCondition(true);

        ContactForm savedContactForm = new ContactForm();
        savedContactForm.setContactFormId(1L);
        savedContactForm.setCompanyName(contactFormInputDto.getCompanyName());
        savedContactForm.setName(contactFormInputDto.getName());
        savedContactForm.setPhoneNumber(contactFormInputDto.getPhoneNumber());
        savedContactForm.setEMail(contactFormInputDto.getEMail());
        savedContactForm.setDescription(contactFormInputDto.getDescription());
        savedContactForm.setTermsOfCondition(contactFormInputDto.getTermsOfCondition());

        when(contactFormRepository.save(any(ContactForm.class))).thenReturn(savedContactForm);

        // Act
        ContactFormOutputDto contactFormOutputDto = contactFormService.createContactForm(contactFormInputDto);

        // Assert
        assertEquals(savedContactForm.getContactFormId(), contactFormOutputDto.getContactFormId());
        assertEquals(savedContactForm.getCompanyName(), contactFormOutputDto.getCompanyName());
        assertEquals(savedContactForm.getName(), contactFormOutputDto.getName());
        assertEquals(savedContactForm.getPhoneNumber(), contactFormOutputDto.getPhoneNumber());
        assertEquals(savedContactForm.getEMail(), contactFormOutputDto.getEMail());
        assertEquals(savedContactForm.getDescription(), contactFormOutputDto.getDescription());
        assertEquals(savedContactForm.getTermsOfCondition(), contactFormOutputDto.getTermsOfCondition());
    }

    @Test
    void updateContactForm() {
        // Arrange
        ContactFormInputDto updatedContactFormDto = new ContactFormInputDto();
        updatedContactFormDto.setCompanyName("updatedcompany");
        updatedContactFormDto.setName("updated");
        updatedContactFormDto.setPhoneNumber(98765432);
        updatedContactFormDto.setEMail("updated@email.nl");
        updatedContactFormDto.setDescription("updateddescription");
        updatedContactFormDto.setTermsOfCondition(true);

        ContactForm existingContactForm = new ContactForm();
        existingContactForm.setContactFormId(1L);
        existingContactForm.setCompanyName("testcompany");
        existingContactForm.setName("test");
        existingContactForm.setPhoneNumber(12345678);
        existingContactForm.setEMail("test@test.com");
        existingContactForm.setDescription("testdescription");
        existingContactForm.setTermsOfCondition(false);

        when(contactFormRepository.findById(anyLong())).thenReturn(Optional.of(existingContactForm));
        when(contactFormRepository.save(any(ContactForm.class))).thenReturn(existingContactForm);

        // Act
        ContactFormOutputDto updatedContactFormOutputDto = contactFormService.updateContactForm(1L, updatedContactFormDto);

        // Assert
        assertEquals(existingContactForm.getContactFormId(), updatedContactFormOutputDto.getContactFormId());
        assertEquals(updatedContactFormDto.getCompanyName(), updatedContactFormOutputDto.getCompanyName());
        assertEquals(updatedContactFormDto.getName(), updatedContactFormOutputDto.getName());
        assertEquals(updatedContactFormDto.getPhoneNumber(), updatedContactFormOutputDto.getPhoneNumber());
        assertEquals(updatedContactFormDto.getEMail(), updatedContactFormOutputDto.getEMail());
        assertEquals(updatedContactFormDto.getDescription(), updatedContactFormOutputDto.getDescription());
        assertEquals(updatedContactFormDto.getTermsOfCondition(), updatedContactFormOutputDto.getTermsOfCondition());
    }

    @Test
    void deleteContactForm() throws RecordNotFoundException {
        // Arrange
        Long contactFormId = 1L;

        // Mock the existsById() method to return true
        when(contactFormRepository.existsById(contactFormId)).thenReturn(true);

        // Act
        contactFormService.deleteContactForm(contactFormId);

        // Assert
        verify(contactFormRepository, times(1)).deleteById(contactFormId);
    }

    @Test
    void deleteContactForm_RecordNotFoundException() {
        // Arrange
        Long contactFormId = 1L;

        // Mock the existsById() method to return false
        when(contactFormRepository.existsById(contactFormId)).thenReturn(false);

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> contactFormService.deleteContactForm(contactFormId));

        // Verify that deleteById() method is not invoked
        verify(contactFormRepository, never()).deleteById(anyLong());
    }
}