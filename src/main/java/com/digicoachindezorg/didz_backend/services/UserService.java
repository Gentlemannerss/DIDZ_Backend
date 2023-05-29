package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.UserDto;
        import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
        import com.digicoachindezorg.didz_backend.models.Invoice;
import com.digicoachindezorg.didz_backend.models.StudyGroup;
import com.digicoachindezorg.didz_backend.models.User;
        import com.digicoachindezorg.didz_backend.repositories.InvoiceRepository;
import com.digicoachindezorg.didz_backend.repositories.StudyGroupRepository;
import com.digicoachindezorg.didz_backend.repositories.UserRepository;
        import org.springframework.beans.BeanUtils;
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
        this.studyGroupRepository = studyGroupRepository; // Initialize studyGroupRepository
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getUser(Long id) throws RecordNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + id));
        return toUserDto(user);
    }

    public UserDto createUser(UserDto userDto) {
        User user = fromUserDto(userDto);
        User createdUser = userRepository.save(user);
        return toUserDto(createdUser);
    }

    public UserDto updateUser(Long id, UserDto userDtoToUpdate) throws RecordNotFoundException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + id));

        // Update the fields of the existing user
        BeanUtils.copyProperties(userDtoToUpdate, existingUser);

        User updatedUser = userRepository.save(existingUser);
        return toUserDto(updatedUser);
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

    private UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    private User fromUserDto(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
}
