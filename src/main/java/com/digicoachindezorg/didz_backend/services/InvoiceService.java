package com.digicoachindezorg.didz_backend.services;


import com.digicoachindezorg.didz_backend.dtos.input.InvoiceInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.InvoiceOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Invoice;
import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.User;
import com.digicoachindezorg.didz_backend.repositories.InvoiceRepository;
import com.digicoachindezorg.didz_backend.repositories.ProductRepository;
import com.digicoachindezorg.didz_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public InvoiceOutputDto getInvoice(Long invoiceId) throws RecordNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RecordNotFoundException("Invoice not found with ID: " + invoiceId));
        return transferInvoiceToInvoiceOutputDto(invoice);
    }

    public List<InvoiceOutputDto> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(this::transferInvoiceToInvoiceOutputDto)
                .collect(Collectors.toList());
    }

    public InvoiceOutputDto createInvoice(InvoiceInputDto invoiceDto) {
        Invoice invoice = transferInvoiceInputDtoToInvoice(invoiceDto);
        invoice.setOrderDate(LocalDate.now());

        //Iemand die een invoice aanmaakt, geeft gegevens in voor een automatische user. Wat moet ik hiervoor doen?

        // Calculate total product price
        calculateTotalProductPrice(invoice);

        Invoice createdInvoice = invoiceRepository.save(invoice);
        return transferInvoiceToInvoiceOutputDto(createdInvoice);
    }

    private void calculateTotalProductPrice(Invoice invoice) {
        List<Product> products = invoice.getProducts();
        System.out.println(products);
        int amountOfParticipants = invoice.getAmountOfParticipants();

        double totalProductPrice = 0.0;

        for (Product product : products) {
            double productPrice = product.getPrice();
            totalProductPrice += productPrice * amountOfParticipants;
        }

        invoice.setTotalPrice(totalProductPrice);
    }

    public InvoiceOutputDto updateInvoice(Long invoiceId, InvoiceInputDto updatedInvoiceDto) throws RecordNotFoundException {
        Invoice existingInvoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RecordNotFoundException("Invoice not found with ID: " + invoiceId));

        // Update the fields of the existing invoice
        Invoice updatedInvoice = updateInvoiceInputDtoToInvoice(updatedInvoiceDto, existingInvoice);

        Invoice savedInvoice = invoiceRepository.save(updatedInvoice);
        return transferInvoiceToInvoiceOutputDto(savedInvoice);
    }

    public List<InvoiceOutputDto> getInvoicesByUserId(Long userId) {
        List<Invoice> invoices = invoiceRepository.findByUserUserId(userId);
        return invoices.stream()
                .map(this::transferInvoiceToInvoiceOutputDto)
                .collect(Collectors.toList());
    }

    public void deleteInvoice(Long invoiceId) throws RecordNotFoundException {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new RecordNotFoundException("Invoice not found with ID: " + invoiceId);
        }
        invoiceRepository.deleteById(invoiceId);
    }

    private InvoiceOutputDto transferInvoiceToInvoiceOutputDto(Invoice invoice) {
        InvoiceOutputDto invoiceDto = new InvoiceOutputDto();
        invoiceDto.setInvoiceId(invoice.getInvoiceId());
        invoiceDto.setOrderDate(LocalDate.now());
        invoiceDto.setTotalPrice(invoice.getTotalPrice());
        invoiceDto.setAddress(invoice.getAddress());
        /*invoiceDto.setTravelCost(invoice.getTravelCost());*/
        invoiceDto.setUser(invoice.getUser());
        invoiceDto.setProducts(invoice.getProducts());
        invoiceDto.setAmountOfParticipants(invoice.getAmountOfParticipants());
        invoiceDto.setInvoiceAddress(invoice.getInvoiceAddress());
        invoiceDto.setFrequency(invoice.getFrequency());
        invoiceDto.setComments(invoice.getComments());
        invoiceDto.setTermsOfCondition(invoice.getTermsOfCondition());
        return invoiceDto;
    }

    private Invoice transferInvoiceInputDtoToInvoice(InvoiceInputDto invoiceDto) {
        Invoice invoice = new Invoice();
        if (invoiceDto.getOrderDate()!=null) {
            invoice.setOrderDate(invoiceDto.getOrderDate());
        }
        if (invoiceDto.getAddress()!=null) {
            invoice.setAddress(invoiceDto.getAddress());
        }
        if (invoiceDto.getUserId()!=null) {
            User user = userRepository.findById(invoiceDto.getUserId())
                    .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + invoiceDto.getUserId()));
            invoice.setUser(user); //Maak deze methode zoals getProductsID, userRepos maar wel voor een ID, meegeven net zoals producten
        }
        if (invoiceDto.getProductsId()!=null) {
            List<Product> products = productRepository.findAllById(invoiceDto.getProductsId()); //Nu haal ik de ID van producten op, en hiermee haal ik producten uit de database, en geef ik gelijk mee.
            System.out.println(products);
            invoice.setProducts(products);
        }
        if (invoiceDto.getAmountOfParticipants()!=null) {
            invoice.setAmountOfParticipants(invoiceDto.getAmountOfParticipants());
        }
        if (invoiceDto.getInvoiceAddress()!=null) {
            invoice.setInvoiceAddress(invoiceDto.getInvoiceAddress());
        }
        if (invoiceDto.getFrequency()!=null) {
            invoice.setFrequency(invoiceDto.getFrequency());
        }
        if (invoiceDto.getComments()!=null) {
            invoice.setComments(invoiceDto.getComments());
        }
        if (invoiceDto.getTermsOfCondition()!=null) {
            invoice.setTermsOfCondition(invoiceDto.getTermsOfCondition());
        }
        return invoice;
    }

    private Invoice updateInvoiceInputDtoToInvoice(InvoiceInputDto invoiceDto, Invoice invoice) {
        if (invoiceDto.getOrderDate()!=null) {
            invoice.setOrderDate(invoiceDto.getOrderDate());
        }
        if (invoiceDto.getAddress()!=null) {
            invoice.setAddress(invoiceDto.getAddress());
        }
        if (invoiceDto.getUserId()!=null) {
            User user = userRepository.findById(invoiceDto.getUserId())
                    .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + invoiceDto.getUserId()));
            invoice.setUser(user);
        }
        if (invoiceDto.getProductsId()!=null) {
            List<Product> products = productRepository.findAllById(invoiceDto.getProductsId());
            invoice.setProducts(products);
        }
        if (invoiceDto.getAmountOfParticipants()!=null) {
            invoice.setAmountOfParticipants(invoiceDto.getAmountOfParticipants());
        }
        if (invoiceDto.getInvoiceAddress()!=null) {
            invoice.setInvoiceAddress(invoiceDto.getInvoiceAddress());
        }
        if (invoiceDto.getFrequency()!=null) {
            invoice.setFrequency(invoiceDto.getFrequency());
        }
        if (invoiceDto.getComments()!=null) {
            invoice.setComments(invoiceDto.getComments());
        }
        if (invoiceDto.getTermsOfCondition()!=null) {
            invoice.setTermsOfCondition(invoiceDto.getTermsOfCondition());
        }
        return invoice;
    }
}