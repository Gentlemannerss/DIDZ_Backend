package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.ContactFormDto;
import com.digicoachindezorg.didz_backend.services.ContactFormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ContactFormController {

    @GetMapping
    public ResponseEntity<List<ContactFormDto>> getAllContactForms() {
        return ResponseEntity.ok().body(ContactFormService.getAllContactForms());
    }
}
