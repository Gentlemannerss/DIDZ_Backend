package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.input.ContactFormInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.ContactFormOutputDto;
import com.digicoachindezorg.didz_backend.filter.JwtRequestFilter;
import com.digicoachindezorg.didz_backend.models.ContactForm;
import com.digicoachindezorg.didz_backend.models.User;
import com.digicoachindezorg.didz_backend.services.ContactFormService;
import com.digicoachindezorg.didz_backend.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ContactFormController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class ContactFormControllerTest {

    @Autowired
    MockMvc mockMvc; //Dit gebruik je om requests te sturen naar de controller

    @MockBean
    ContactFormService contactFormService;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @MockBean
    UserService userService;

    @Test
    void createContactForm() throws Exception {
        // Create a ContactFormInputDto object with the desired data
        ContactFormInputDto contactFormDto = new ContactFormInputDto();
        contactFormDto.setCompanyName("testcompany");
        contactFormDto.setName("test");
        contactFormDto.setPhoneNumber(12345678);
        contactFormDto.setEMail("test@email.nl");
        contactFormDto.setDescription("testdescription");
        contactFormDto.setTermsOfCondition(true);

        // Create a ContactFormOutputDto object with the expected data
        ContactFormOutputDto expectedOutputDto = new ContactFormOutputDto();
        expectedOutputDto.setContactFormId(1L);
        expectedOutputDto.setCompanyName("testcompany");
        expectedOutputDto.setName("test");
        expectedOutputDto.setPhoneNumber(12345678);
        expectedOutputDto.setEMail("test@email.nl");
        expectedOutputDto.setDescription("testdescription");
        expectedOutputDto.setTermsOfCondition(true);

        // Mock the createContactForm method to return the expected outputDto
        Mockito.when(contactFormService.createContactForm(Mockito.any(ContactFormInputDto.class)))
                .thenReturn(expectedOutputDto);

        // Perform the test by sending a POST request to the /contactform endpoint
        this.mockMvc.perform(MockMvcRequestBuilders.post("/contactform")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"companyName\":\"testcompany\",\"name\":\"test\",\"phoneNumber\":12345678,\"eMail\":\"test@email.nl\",\"description\":\"testdescription\",\"termsOfCondition\":true}")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactFormId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName").value("testcompany"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(12345678))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eMail").value("test@email.nl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("testdescription"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.termsOfCondition").value(true));
    }

    @Test
    void updateContactForm() throws Exception {
        // Create a ContactFormInputDto object with the desired data
        ContactFormInputDto contactFormDto = new ContactFormInputDto();
        contactFormDto.setCompanyName("updatedcompany");
        contactFormDto.setName("updated");
        contactFormDto.setPhoneNumber(98765432);
        contactFormDto.setEMail("updated@test.com");
        contactFormDto.setDescription("updated description");
        contactFormDto.setTermsOfCondition(true);

        // Create a ContactFormOutputDto object with the expected data
        ContactFormOutputDto expectedOutputDto = new ContactFormOutputDto();
        expectedOutputDto.setContactFormId(1L);
        expectedOutputDto.setCompanyName("updatedcompany");
        expectedOutputDto.setName("updated");
        expectedOutputDto.setPhoneNumber(98765432);
        expectedOutputDto.setEMail("updated@test.com");
        expectedOutputDto.setDescription("updated description");
        expectedOutputDto.setTermsOfCondition(true);

        // Mock the updateContactForm method to return the expected outputDto
        Mockito.when(contactFormService.updateContactForm(Mockito.anyLong(), Mockito.any(ContactFormInputDto.class)))
                .thenReturn(expectedOutputDto);

        // Perform the test by sending a PUT request to the /contactform/{id} endpoint
        this.mockMvc.perform(MockMvcRequestBuilders.put("/contactform/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"companyName\":\"updatedcompany\",\"name\":\"updated\",\"phoneNumber\":98765432,\"eMail\":\"updated@test.com\",\"description\":\"updated description\",\"termsOfCondition\":true}")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactFormId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName").value("updatedcompany"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("updated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(98765432))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eMail").value("updated@test.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("updated description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.termsOfCondition").value(true));
    }

    @Test
    void deleteContactForm() throws Exception {
        Long contactFormId = 1L;

        // Mock the deleteContactForm method
        Mockito.doNothing().when(contactFormService).deleteContactForm(contactFormId);

        // Perform the test by sending a DELETE request to the /contactform/{id} endpoint
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/contactform/{id}", contactFormId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void getContactForm() throws Exception {
        Long contactFormId = 1L;

        // Create a ContactFormOutputDto object with the expected data
        ContactFormOutputDto expectedOutputDto = new ContactFormOutputDto();
        expectedOutputDto.setContactFormId(contactFormId);
        expectedOutputDto.setCompanyName("testcompany");
        expectedOutputDto.setName("test");
        expectedOutputDto.setPhoneNumber(12345678);
        expectedOutputDto.setEMail("test@example.com");
        expectedOutputDto.setDescription("test description");
        expectedOutputDto.setTermsOfCondition(true);

        // Mock the getContactForm method to return the expected outputDto
        Mockito.when(contactFormService.getContactForm(contactFormId))
                .thenReturn(expectedOutputDto);

        // Perform the test by sending a GET request to the /contactform/{id} endpoint
        this.mockMvc.perform(MockMvcRequestBuilders.get("/contactform/{id}", contactFormId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactFormId").value(contactFormId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName").value("testcompany"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(12345678))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eMail").value("test@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("test description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.termsOfCondition").value(true));
    }

    @Test
    void getAllContactForms() throws Exception {
        // Create a list of ContactFormOutputDto objects with the expected data
        List<ContactFormOutputDto> expectedOutputDtos = new ArrayList<>();

        ContactFormOutputDto contactForm1 = new ContactFormOutputDto();
        contactForm1.setContactFormId(1L);
        contactForm1.setCompanyName("company1");
        contactForm1.setName("name1");
        contactForm1.setPhoneNumber(123456789);
        contactForm1.setEMail("email1@example.com");
        contactForm1.setDescription("description1");
        contactForm1.setTermsOfCondition(true);
        expectedOutputDtos.add(contactForm1);

        ContactFormOutputDto contactForm2 = new ContactFormOutputDto();
        contactForm2.setContactFormId(2L);
        contactForm2.setCompanyName("company2");
        contactForm2.setName("name2");
        contactForm2.setPhoneNumber(987654321);
        contactForm2.setEMail("email2@example.com");
        contactForm2.setDescription("description2");
        contactForm2.setTermsOfCondition(false);
        expectedOutputDtos.add(contactForm2);

        // Mock the getAllContactForms method to return the expected outputDtos
        Mockito.when(contactFormService.getAllContactForms())
                .thenReturn(expectedOutputDtos);

        // Perform the test by sending a GET request to the /contactform endpoint
        this.mockMvc.perform(MockMvcRequestBuilders.get("/contactform"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(expectedOutputDtos.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].contactFormId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].companyName").value("company1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("name1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber").value(123456789))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].eMail").value("email1@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("description1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].termsOfCondition").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].contactFormId").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].companyName").value("company2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("name2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phoneNumber").value(987654321))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].eMail").value("email2@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("description2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].termsOfCondition").value(false));
    }
}