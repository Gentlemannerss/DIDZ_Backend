package com.digicoachindezorg.didz_backend;

import com.digicoachindezorg.didz_backend.controllers.ContactFormController;
import com.digicoachindezorg.didz_backend.dtos.input.ContactFormInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.ContactFormOutputDto;
import com.digicoachindezorg.didz_backend.services.ContactFormService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class ContactFormControllerIntergrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void createContactForm() throws Exception {
        String requestJson = "{\"contactFormId\":1,\"companyName\":\"testcompany\",\"name\":\"test\",\"phoneNumber\":12345678,\"eMail\":\"test@email.nl\",\"description\":\"testdescription\",\"termsOfCondition\":true}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/contactform")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                )
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.contactFormId").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.companyName").value("testcompany"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.name").value("test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.phoneNumber").value(12345678))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.eMail").value("test@email.nl"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.description").value("testdescription"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.termsOfCondition").value(true));
    }

    /* todo: fix get integration test.
    @Test
    void getAllContactForms() throws Exception {
        List<ContactFormOutputDto> contactForms = Arrays.asList(
                new ContactFormOutputDto(1L, "testcompany1", "test1", 12345678, "test1@email.nl", "testdescription1", true),
                new ContactFormOutputDto(2L, "testcompany2", "test2", 87654321, "test2@email.nl", "testdescription2", false)
        );

        // Mock the ContactFormService and return the list of contact forms when requested
        ContactFormService contactFormService = Mockito.mock(ContactFormService.class);
        Mockito.when(contactFormService.getAllContactForms()).thenReturn(contactForms);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/contactform"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().json("[{\"contactFormId\":1,\"companyName\":\"testcompany1\",\"name\":\"test1\",\"phoneNumber\":12345678,\"eMail\":\"test1@email.nl\",\"description\":\"testdescription1\",\"termsOfCondition\":true},{\"contactFormId\":2,\"companyName\":\"testcompany2\",\"name\":\"test2\",\"phoneNumber\":87654321,\"eMail\":\"test2@email.nl\",\"description\":\"testdescription2\",\"termsOfCondition\":false}]"));
    }

    @Test
    void getContactForm() throws Exception {
        ContactFormOutputDto contactForm = new ContactFormOutputDto(1L, "testcompany", "test", 12345678, "test@example.com", "testdescription", true);

        // Mock the ContactFormService and return the contact form when requested
        ContactFormService contactFormService = Mockito.mock(ContactFormService.class);
        Mockito.when(contactFormService.getContactForm(1L)).thenReturn(contactForm);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/contactform/{id}", 1))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.contactFormId").value(1L))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.companyName").value("testcompany"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.name").value("test"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.phoneNumber").value(12345678))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.eMail").value("test@example.com"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.description").value("testdescription"))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.termsOfCondition").value(true));
    }
    */

    @Test
    void deleteContactForm() throws Exception {
        ContactFormService contactFormService = Mockito.mock(ContactFormService.class);
        Mockito.doNothing().when(contactFormService).deleteContactForm(1L);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/contactform/{id}", 1))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateContactForm() throws Exception {
        // Arrange
        String requestJson = "{\"companyName\":\"testcompany\",\"name\":\"test\",\"phoneNumber\":12345678,\"eMail\":\"test@example.com\",\"description\":\"testdescription\",\"termsOfCondition\":true}";

        // Mock the ContactFormService and handle the update of a contact form
        ContactFormService contactFormService = Mockito.mock(ContactFormService.class);
        ContactFormOutputDto updatedContactForm = new ContactFormOutputDto(1L, "testcompany", "test", 12345678, "test@example.com", "testdescription", true);
        Mockito.when(contactFormService.updateContactForm(Mockito.anyLong(), Mockito.any(ContactFormInputDto.class))).thenReturn(updatedContactForm);

        // Create an instance of the controller and set the mocked service
        ContactFormController contactFormController = new ContactFormController(contactFormService);

        // Set up the MockMvc instance with the controller
        mockMvc = MockMvcBuilders.standaloneSetup(contactFormController).build();

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/contactform/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyName").value("testcompany"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(12345678))
                .andExpect(MockMvcResultMatchers.jsonPath("$.eMail").value("test@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("testdescription"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.termsOfCondition").value(true));
    }
}
