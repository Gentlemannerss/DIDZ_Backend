package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.input.InvoiceInputDto;
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

    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceOutputDto> getInvoice(@PathVariable("invoiceId") Long invoiceId) throws RecordNotFoundException {
        InvoiceOutputDto invoiceDto = invoiceService.getInvoice(invoiceId);
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InvoiceOutputDto>> getInvoicesByUserId(@PathVariable("userId") Long userId) {
        List<InvoiceOutputDto> invoices = invoiceService.getInvoicesByUserId(userId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
    /*Doen deze twee niet exact hetzelfde?*/
    @GetMapping("/user/{userId}/all")
    public ResponseEntity<List<InvoiceOutputDto>> getAllInvoicesByUserId(@PathVariable("userId") Long userId) {
        List<InvoiceOutputDto> invoices = invoiceService.getAllInvoicesByUserId(userId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceOutputDto>> getAllInvoices() {
        List<InvoiceOutputDto> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InvoiceOutputDto> createInvoice(@RequestBody InvoiceInputDto invoiceDto) {
        InvoiceOutputDto createdInvoice = invoiceService.createInvoice(invoiceDto);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
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
}
