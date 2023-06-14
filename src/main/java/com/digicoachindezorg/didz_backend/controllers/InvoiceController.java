package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.input.InvoiceInputDto;
import com.digicoachindezorg.didz_backend.dtos.input.InvoiceWithExistingUserInputDto;
import com.digicoachindezorg.didz_backend.dtos.input.InvoiceWithNewUserInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.InvoiceOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @PostMapping("/new-user")
    public ResponseEntity<InvoiceOutputDto> createInvoiceWithNewUser(@RequestBody InvoiceWithNewUserInputDto invoiceDto) {
        return new ResponseEntity<>(invoiceService.createInvoiceWithNewUser(invoiceDto), HttpStatus.CREATED);
    }
    @PostMapping("/existing-user")
    public ResponseEntity<InvoiceOutputDto> createInvoiceWithExistingUser(@RequestBody InvoiceWithExistingUserInputDto invoiceDto) {
        return new ResponseEntity<>(invoiceService.createInvoiceWithExistingUser(invoiceDto), HttpStatus.CREATED);
    }
    @PutMapping("/{invoiceId}")
    public ResponseEntity<InvoiceOutputDto> updateInvoice(
            @PathVariable("invoiceId") Long invoiceId,
            @RequestBody InvoiceInputDto updatedInvoiceDto
    ) throws RecordNotFoundException {
        InvoiceOutputDto updatedInvoice = invoiceService.updateInvoice(invoiceId, updatedInvoiceDto);
        return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
    }
    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable("invoiceId") Long invoiceId) throws RecordNotFoundException {
        invoiceService.deleteInvoice(invoiceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceOutputDto> getInvoice(@PathVariable("invoiceId") Long invoiceId) throws RecordNotFoundException {
        return new ResponseEntity<>(invoiceService.getInvoice(invoiceId), HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InvoiceOutputDto>> getInvoicesByUserId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(invoiceService.getInvoicesByUserId(userId), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<InvoiceOutputDto>> getAllInvoices() {
        return new ResponseEntity<>(invoiceService.getAllInvoices(), HttpStatus.OK);
    }
}
