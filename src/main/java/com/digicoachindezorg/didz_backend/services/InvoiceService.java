package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.InvoiceDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Invoice;
import com.digicoachindezorg.didz_backend.repositories.InvoiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public InvoiceDto getInvoice(Long invoiceId) throws RecordNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RecordNotFoundException("Invoice not found with ID: " + invoiceId));
        return toInvoiceDto(invoice);
    }

    public List<InvoiceDto> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(this::toInvoiceDto)
                .collect(Collectors.toList());
    }

    public InvoiceDto createInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = fromInvoiceDto(invoiceDto);
        Invoice createdInvoice = invoiceRepository.save(invoice);
        return toInvoiceDto(createdInvoice);
    }

    public InvoiceDto updateInvoice(Long invoiceId, InvoiceDto updatedInvoiceDto) throws RecordNotFoundException {
        Invoice existingInvoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RecordNotFoundException("Invoice not found with ID: " + invoiceId));

        // Update the fields of the existing invoice
        BeanUtils.copyProperties(updatedInvoiceDto, existingInvoice);

        Invoice updatedInvoice = invoiceRepository.save(existingInvoice);
        return toInvoiceDto(updatedInvoice);
    }

    public List<InvoiceDto> getInvoicesByUserId(Long userId) {
        List<Invoice> invoices = invoiceRepository.findByUserUserId(userId);
        return invoices.stream()
                .map(this::toInvoiceDto)
                .collect(Collectors.toList());
    }

    public List<InvoiceDto> getAllInvoicesByUserId(Long userId) {
        List<Invoice> invoices = invoiceRepository.findAllByUserUserId(userId);
        return invoices.stream()
                .map(this::toInvoiceDto)
                .collect(Collectors.toList());
    }

    public void deleteInvoice(Long invoiceId) throws RecordNotFoundException {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new RecordNotFoundException("Invoice not found with ID: " + invoiceId);
        }
        invoiceRepository.deleteById(invoiceId);
    }

    private InvoiceDto toInvoiceDto(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
        BeanUtils.copyProperties(invoice, invoiceDto);
        // Additional mapping for nested entities
        return invoiceDto;
    }

    private Invoice fromInvoiceDto(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDto, invoice);
        // Additional mapping for nested entities
        return invoice;
    }
}