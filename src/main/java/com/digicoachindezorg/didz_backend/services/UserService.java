package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.input.PasswordInputDto;
import com.digicoachindezorg.didz_backend.dtos.input.UserInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.UserOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.exceptions.UserNotFoundException;
import com.digicoachindezorg.didz_backend.models.Authority;
import com.digicoachindezorg.didz_backend.models.User;
import com.digicoachindezorg.didz_backend.repositories.UserRepository;
import com.digicoachindezorg.didz_backend.utils.RandomStringGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public User getUserByUsername(String username) throws RecordNotFoundException {
        return userRepository.findByUsername(username);
    }

    public UserOutputDto createUser(UserInputDto userInputDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        User user = transferUserInputDtoToUser(userInputDto);
        userRepository.save(user);  // Save the user to generate the id
        user.addAuthority(new Authority(user.getId(), "ROLE_USER"));
        user.setApikey(randomString);
        User createdUser = userRepository.save(user);
        return transferUserToUserOutputDto(createdUser);
    }

    public UserOutputDto updateUser(Long id, UserInputDto userInputDtoToUpdate) throws RecordNotFoundException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + id));
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

    public Set<Authority> getAuthorities(Long userId) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        User user = userRepository.findById(userId).get();
        UserOutputDto userDto = transferUserToUserOutputDto(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(Long userId, String authority) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        User user = userRepository.findById(userId).get();
        user.addAuthority(new Authority(userId, authority));
        userRepository.save(user);
    }

    public void removeAuthority(Long userId, String authority) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        User user = userRepository.findById(userId).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    public String updatePassword(Long userId, PasswordInputDto passwordInputDto) {
        if (!userRepository.existsById(userId)) {
            throw new RecordNotFoundException("The user with userId: " + userId + " doesn't exist.");
        }
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new RecordNotFoundException("The user with userId: " + userId + " doesn't exist.");
        }
        user.get().setPassword(passwordEncoder.encode(passwordInputDto.newPassword));

        userRepository.save(user.get());

        return "The password has been updated sucessfully.";
    }

    public void deleteAllEmails(Long userId) {
        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        User user = userRepository.findById(userId).get();
        user.setPrivateEMail(null);
        user.setWorkEMail(null);
        userRepository.save(user);
    }

    private UserOutputDto transferUserToUserOutputDto(User user) {
        UserOutputDto userDto = new UserOutputDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setAuthorities(user.getAuthorities());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setPrivateEMail(user.getPrivateEMail());
        userDto.setWorkEMail(user.getWorkEMail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddress(user.getAddress());
        userDto.setCompanyName(user.getCompanyName());
        userDto.setInvoices(user.getInvoices());
        userDto.setStudyGroups(user.getStudyGroups());
        userDto.setReviews(user.getReviews());
        userDto.setMessages(user.getMessages());
        userDto.setContactForms(user.getContactForms());
        return userDto;
    }

    private User transferUserInputDtoToUser(UserInputDto userDto) {
        User user = new User();
        if (userDto.getUsername()!=null) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword()!=null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        if (userDto.getFullName()!=null) {
            user.setFullName(userDto.getFullName());
        }
        if (userDto.getDateOfBirth()!=null) {
            user.setDateOfBirth(userDto.getDateOfBirth());
        }
        if (userDto.getPrivateEMail()!=null) {
            user.setPrivateEMail(userDto.getPrivateEMail());
        }
        if (userDto.getWorkEMail()!=null) {
            user.setWorkEMail(userDto.getWorkEMail());
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

    private User updateUserInputDtoToUser(UserInputDto userDto, User user) {
        if (userDto.getUsername()!=null) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getFullName()!=null) {
            user.setFullName(userDto.getFullName());
        }
        if (userDto.getPassword()!=null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        if (userDto.getDateOfBirth()!=null) {
            user.setDateOfBirth(userDto.getDateOfBirth());
        }
        if (userDto.getPrivateEMail()!=null) {
            user.setPrivateEMail(userDto.getPrivateEMail());
        }
        if (userDto.getWorkEMail()!=null) {
            user.setWorkEMail(userDto.getWorkEMail());
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