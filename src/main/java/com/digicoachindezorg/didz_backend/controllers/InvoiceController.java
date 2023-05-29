package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.InvoiceDto;
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
    public ResponseEntity<InvoiceDto> getInvoice(@PathVariable("invoiceId") Long invoiceId) throws RecordNotFoundException {
        InvoiceDto invoiceDto = invoiceService.getInvoice(invoiceId);
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InvoiceDto>> getInvoicesByUserId(@PathVariable("userId") Long userId) {
        List<InvoiceDto> invoices = invoiceService.getInvoicesByUserId(userId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/all")
    public ResponseEntity<List<InvoiceDto>> getAllInvoicesByUserId(@PathVariable("userId") Long userId) {
        List<InvoiceDto> invoices = invoiceService.getAllInvoicesByUserId(userId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAllInvoices() {
        List<InvoiceDto> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> createInvoice(@RequestBody InvoiceDto invoiceDto) {
        InvoiceDto createdInvoice = invoiceService.createInvoice(invoiceDto);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<InvoiceDto> updateInvoice(
            @PathVariable("invoiceId") Long invoiceId,
            @RequestBody InvoiceDto updatedInvoiceDto
    ) throws RecordNotFoundException {
        InvoiceDto updatedInvoice = invoiceService.updateInvoice(invoiceId, updatedInvoiceDto);
        return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable("invoiceId") Long invoiceId) throws RecordNotFoundException {
        invoiceService.deleteInvoice(invoiceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
