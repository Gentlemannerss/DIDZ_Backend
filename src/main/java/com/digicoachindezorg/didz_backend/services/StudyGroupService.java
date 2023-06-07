package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.input.StudyGroupInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.StudyGroupOutputDto;
import com.digicoachindezorg.didz_backend.dtos.output.UserOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.StudyGroup;
import com.digicoachindezorg.didz_backend.models.User;
import com.digicoachindezorg.didz_backend.repositories.ProductRepository;
import com.digicoachindezorg.didz_backend.repositories.StudyGroupRepository;
import com.digicoachindezorg.didz_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyGroupService {

    private final StudyGroupRepository studyGroupRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public StudyGroupService(StudyGroupRepository studyGroupRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.studyGroupRepository = studyGroupRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<StudyGroupOutputDto> getAllStudyGroups() {
        List<StudyGroup> studyGroups = studyGroupRepository.findAll();
        return studyGroups.stream()
                .map(this::transferStudyGroupToStudyGroupOutputDto)
                .collect(Collectors.toList());
    }

    public List<StudyGroupOutputDto> getStudyGroupsByProduct(Long productId) {
        List<StudyGroup> studyGroups = studyGroupRepository.findByProduct_ProductId(productId);
        return studyGroups.stream()
                .map(this::transferStudyGroupToStudyGroupOutputDto)
                .collect(Collectors.toList());
    }

    public StudyGroupOutputDto getStudyGroup(Long id) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + id));
        return transferStudyGroupToStudyGroupOutputDto(studyGroup);
    }

    public StudyGroupOutputDto createStudyGroup(StudyGroupInputDto studyGroupInputDto) {
        StudyGroup studyGroup = transferStudyGroupInputDtoToStudyGroup(studyGroupInputDto);
        StudyGroup createdStudyGroup = studyGroupRepository.save(studyGroup);
        return transferStudyGroupToStudyGroupOutputDto(createdStudyGroup);
    }

    public StudyGroupOutputDto updateStudyGroup(Long id, StudyGroupInputDto studyGroupInputDtoToUpdate) throws RecordNotFoundException {
        StudyGroup existingStudyGroup = studyGroupRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + id));

        // Update the fields of the existing study group
        StudyGroup updatedStudyGroup = updateStudyGroupInputDtoToStudyGroup(studyGroupInputDtoToUpdate, existingStudyGroup);

        StudyGroup savedStudyGroup = studyGroupRepository.save(updatedStudyGroup);
        return transferStudyGroupToStudyGroupOutputDto(savedStudyGroup);
    }

    public StudyGroupOutputDto addUserToStudyGroup(Long studyGroupId, Long userId) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + studyGroupId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + userId));

        studyGroup.getUsers().add(user);
        StudyGroup updatedStudyGroup = studyGroupRepository.save(studyGroup);
        return transferStudyGroupToStudyGroupOutputDto(updatedStudyGroup);
    }

    public StudyGroupOutputDto removeUserFromStudyGroup(Long studyGroupId, Long userId) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + studyGroupId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + userId));

        studyGroup.getUsers().remove(user);
        StudyGroup updatedStudyGroup = studyGroupRepository.save(studyGroup);
        return transferStudyGroupToStudyGroupOutputDto(updatedStudyGroup);
    }


    public List<UserOutputDto> getStudyGroupUsers(Long studyGroupId) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + studyGroupId));

        return studyGroup.getUsers().stream()
                .map(user -> {
                    UserOutputDto userOutputDto = new UserOutputDto();
                    userOutputDto.setId(user.getId());
                    userOutputDto.setUsername(user.getUsername());
                    userOutputDto.setFullName(user.getFullName());
                    userOutputDto.setEMail(user.getEMail());
                    userOutputDto.setDateOfBirth(user.getDateOfBirth());
                    userOutputDto.setAddress(user.getAddress());
                    userOutputDto.setAuthority(user.getAuthority());
                    userOutputDto.setAvailability(user.getAvailability());
                    userOutputDto.setCompanyName(user.getCompanyName());
                    userOutputDto.setPhoneNumber(user.getPhoneNumber());
                    return userOutputDto;
                })
                .collect(Collectors.toList());
    }

    public void deleteStudyGroup(Long id) throws RecordNotFoundException {
        if (!studyGroupRepository.existsById(id)) {
            throw new RecordNotFoundException("Study group not found with id: " + id);
        }
        studyGroupRepository.deleteById(id);
    }

    private StudyGroupOutputDto transferStudyGroupToStudyGroupOutputDto(StudyGroup studyGroup) {
        StudyGroupOutputDto studyGroupDto = new StudyGroupOutputDto();
        studyGroupDto.setGroupId(studyGroup.getGroupId());
        studyGroupDto.setGroupName(studyGroup.getGroupName());
        studyGroupDto.setUsers(studyGroup.getUsers());
        studyGroupDto.setProduct(studyGroup.getProduct());
        studyGroupDto.setPinboardMessages(studyGroup.getMessageBoard());
        return studyGroupDto;
    }

    private StudyGroup transferStudyGroupInputDtoToStudyGroup(StudyGroupInputDto studyGroupDto) {
        StudyGroup studyGroup = new StudyGroup();
        if (studyGroupDto.getGroupName()!=null) {
            studyGroup.setGroupName(studyGroupDto.getGroupName());
        }
        if (studyGroupDto.getUserIds()!=null) {
            List<User> users = userRepository.findAllById(studyGroupDto.getUserIds());
            studyGroup.setUsers(users);
        }
        if (studyGroupDto.getProductId()!=null) {
            Product product = productRepository.findById(studyGroupDto.getProductId())
                    .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + studyGroupDto.getProductId()));
            studyGroup.setProduct(product);
        }
        return studyGroup;
    }

    private StudyGroup updateStudyGroupInputDtoToStudyGroup(StudyGroupInputDto studyGroupDto, StudyGroup studyGroup) {
        if (studyGroupDto.getGroupName()!=null) {
            studyGroup.setGroupName(studyGroupDto.getGroupName());
        }
        if (studyGroupDto.getUserIds()!=null) {
            List<User> users = userRepository.findAllById(studyGroupDto.getUserIds());
            studyGroup.setUsers(users);
        }
        if (studyGroupDto.getProductId()!=null) {
            Product product = productRepository.findById(studyGroupDto.getProductId())
                    .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + studyGroupDto.getProductId()));
            studyGroup.setProduct(product);
        }
        return studyGroup;
    }
}