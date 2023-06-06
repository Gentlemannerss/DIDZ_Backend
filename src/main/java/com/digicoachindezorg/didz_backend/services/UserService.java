package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.input.UserInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.UserOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Invoice;
import com.digicoachindezorg.didz_backend.models.StudyGroup;
import com.digicoachindezorg.didz_backend.models.User;
import com.digicoachindezorg.didz_backend.repositories.InvoiceRepository;
import com.digicoachindezorg.didz_backend.repositories.StudyGroupRepository;
import com.digicoachindezorg.didz_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final InvoiceRepository invoiceRepository;
    private final StudyGroupRepository studyGroupRepository;

    public UserService(UserRepository userRepository, InvoiceRepository invoiceRepository, StudyGroupRepository studyGroupRepository) {
        this.userRepository = userRepository;
        this.invoiceRepository = invoiceRepository;
        this.studyGroupRepository = studyGroupRepository;
    }

    public List<UserOutputDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::transferUserToUserOutputDto)
                .collect(Collectors.toList());
    }

    public UserOutputDto getUser(Long id) throws RecordNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + id));
        return transferUserToUserOutputDto(user);
    }

    public UserOutputDto createUser(UserInputDto userInputDto) {
        User user = transferUserInputDtoToUser(userInputDto);
        User createdUser = userRepository.save(user);
        return transferUserToUserOutputDto(createdUser);
    }

    public UserOutputDto updateUser(Long id, UserInputDto userInputDtoToUpdate) throws RecordNotFoundException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + id));

        // Update the fields of the existing user
        User updatedUser = updateUserInputDtoToUser(userInputDtoToUpdate, existingUser);

        User savedUser = userRepository.save(updatedUser);
        return transferUserToUserOutputDto(savedUser);
    }

    public void deleteUser(Long id) throws RecordNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new RecordNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public void assignUserToStudyGroup(Long userId, Long studyGroupId) throws RecordNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + userId));

        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new RecordNotFoundException("Study Group not found with id: " + studyGroupId));

        user.getStudyGroups().add(studyGroup);
        userRepository.save(user);
    }

    public void assignUserToInvoice(Long userId, Long invoiceId) throws RecordNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + userId));

        // Assign the user to the invoice
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RecordNotFoundException("Invoice not found with id: " + invoiceId));

        user.getInvoices().add(invoice);
        userRepository.save(user);
    }

    private UserOutputDto transferUserToUserOutputDto(User user) {
        UserOutputDto userDto = new UserOutputDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setEMail(user.getEMail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddress(user.getAddress());
        userDto.setAuthority(user.getAuthority());
        userDto.setAvailability(user.getAvailability());
        userDto.setCompanyName(user.getCompanyName());
        userDto.setInvoices(user.getInvoices());
        userDto.setStudyGroups(user.getStudyGroups());
        userDto.setReviews(user.getReviews());
        userDto.setMessages(user.getMessages()); // Wat moet ik hier doen om beide messages op te vangen aan een message.
        userDto.setContactForms(user.getContactForms());
        return userDto;
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
        if (userDto.getAuthority()!=null) {
            user.setAuthority(userDto.getAuthority());
        }
        if (userDto.getAvailability()!=null) {
            user.setAvailability(userDto.getAvailability());
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

    private User updateUserInputDtoToUser(UserInputDto userDto, User user) {
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
        if (userDto.getAuthority()!=null) {
            user.setAuthority(userDto.getAuthority());
        }
        if (userDto.getAvailability()!=null) {
            user.setAvailability(userDto.getAvailability());
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