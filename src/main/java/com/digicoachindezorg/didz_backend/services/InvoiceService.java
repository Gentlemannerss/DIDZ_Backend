package com.digicoachindezorg.didz_backend.services;


import com.digicoachindezorg.didz_backend.dtos.input.InvoiceInputDto;
import com.digicoachindezorg.didz_backend.dtos.input.InvoiceWithExistingUserInputDto;
import com.digicoachindezorg.didz_backend.dtos.input.InvoiceWithNewUserInputDto;
import com.digicoachindezorg.didz_backend.dtos.input.UserInputDto;
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

    public InvoiceOutputDto createInvoiceWithExistingUser(InvoiceWithExistingUserInputDto invoiceDto) {
        Invoice invoice = transferInvoiceInputDtoToInvoice(invoiceDto.invoice);
        invoice.setOrderDate(LocalDate.now());

        //Hier kun je de existing User ophalen en deze set je voor de invoice.
        if (invoiceDto.getUserId()!=null) {
            User user = userRepository.findById(invoiceDto.getUserId())
                    .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + invoiceDto.getUserId()));
            invoice.setUser(user);
        }
        // Calculate total product price
        calculateTotalProductPrice(invoice);

        Invoice createdInvoice = invoiceRepository.save(invoice);
        return transferInvoiceToInvoiceOutputDto(createdInvoice);
    }

    public InvoiceOutputDto createInvoiceWithNewUser(InvoiceWithNewUserInputDto invoiceDto) {
        Invoice invoice = transferInvoiceInputDtoToInvoice(invoiceDto.invoice);
        invoice.setOrderDate(LocalDate.now());

        //Hier wordt een nieuwe User aangemaakt en deze set je voor de invoice, hij krijgt de rol van Guest.
        if (invoiceDto.getUser()!=null) {
            User user = transferUserInputDtoToUser(invoiceDto.getUser());
            userRepository.save(user);
            invoice.setUser(user);
        }
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
        /*if (invoiceDto.getUserId()!=null) {
            User user = userRepository.findById(invoiceDto.getUserId())
                    .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + invoiceDto.getUserId()));
            invoice.setUser(user);
        }*/
        if (invoiceDto.getProductsId()!=null) {
            List<Product> products = productRepository.findAllById(invoiceDto.getProductsId());
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

    private User transferUserInputDtoToUser(UserInputDto userDto) {
        User user = new User();
        if (userDto.getUsername()!=null) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getFullName()!=null) {
            user.setFullName(userDto.getFullName());
        }
        if (userDto.getDateOfBirth()!=null) {
            user.setDateOfBirth(userDto.getDateOfBirth());
        }
        if (userDto.getEMail()!=null) {
            user.setEMail(userDto.getEMail());
        }
        if (userDto.getPhoneNumber()!=null) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }
        if (userDto.getAddress()!=null) {
            user.setAddress(userDto.getAddress());
        }
        if (userDto.getCompanyName()!=null) {
            user.setCompanyName(userDto.getCompanyName());
        }
        if (userDto.getInvoices()!=null) {
            user.setInvoices(userDto.getInvoices());
        }
        if (userDto.getStudyGroups()!=null) {
            user.setStudyGroups(userDto.getStudyGroups());
        }
        if (userDto.getReviews()!=null) {
            user.setReviews(userDto.getReviews());
        }
        if (userDto.getMessages()!=null) {
            user.setMessages(userDto.getMessages());
        }
        if (userDto.getContactForms()!=null) {
            user.setContactForms(userDto.getContactForms());
        }
        return user;
    }
}