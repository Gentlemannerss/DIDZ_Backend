package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.StudyGroupDto;
import com.digicoachindezorg.didz_backend.dtos.UserDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.StudyGroup;
import com.digicoachindezorg.didz_backend.models.User;
import com.digicoachindezorg.didz_backend.repositories.StudyGroupRepository;
import com.digicoachindezorg.didz_backend.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyGroupService {

    private final StudyGroupRepository studyGroupRepository;
    private final UserRepository userRepository;

    public StudyGroupService(StudyGroupRepository studyGroupRepository, UserRepository userRepository) {
        this.studyGroupRepository = studyGroupRepository;
        this.userRepository = userRepository;
    }

    public List<StudyGroupDto> getAllStudyGroups() {
        List<StudyGroup> studyGroups = studyGroupRepository.findAll();
        return studyGroups.stream()
                .map(this::toStudyGroupDto)
                .collect(Collectors.toList());
    }

    public List<StudyGroupDto> getStudyGroupsByProduct(Long productId) {
        List<StudyGroup> studyGroups = studyGroupRepository.findByProduct_ProductId(productId);
        return studyGroups.stream()
                .map(this::toStudyGroupDto)
                .collect(Collectors.toList());
    }

    public StudyGroupDto getStudyGroup(Long id) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + id));
        return toStudyGroupDto(studyGroup);
    }

    public StudyGroupDto createStudyGroup(StudyGroupDto studyGroupDto) {
        StudyGroup studyGroup = fromStudyGroupDto(studyGroupDto);
        StudyGroup createdStudyGroup = studyGroupRepository.save(studyGroup);
        return toStudyGroupDto(createdStudyGroup);
    }

    public StudyGroupDto updateStudyGroup(Long id, StudyGroupDto studyGroupDtoToUpdate) throws RecordNotFoundException {
        StudyGroup existingStudyGroup = studyGroupRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + id));

        // Update the fields of the existing study group
        BeanUtils.copyProperties(studyGroupDtoToUpdate, existingStudyGroup);

        StudyGroup updatedStudyGroup = studyGroupRepository.save(existingStudyGroup);
        return toStudyGroupDto(updatedStudyGroup);
    }

    public StudyGroupDto addUserToStudyGroup(Long studyGroupId, Long userId) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + studyGroupId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + userId));

        studyGroup.getUsers().add(user);
        StudyGroup updatedStudyGroup = studyGroupRepository.save(studyGroup);
        return toStudyGroupDto(updatedStudyGroup);
    }

    public StudyGroupDto removeUserFromStudyGroup(Long studyGroupId, Long userId) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + studyGroupId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + userId));

        studyGroup.getUsers().remove(user);
        StudyGroup updatedStudyGroup = studyGroupRepository.save(studyGroup);
        return toStudyGroupDto(updatedStudyGroup);
    }

    public List<UserDto> getStudyGroupUsers(Long studyGroupId) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + studyGroupId));

        return studyGroup.getUsers().stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    BeanUtils.copyProperties(user, userDto);
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    public void deleteStudyGroup(Long id) throws RecordNotFoundException {
        if (!studyGroupRepository.existsById(id)) {
            throw new RecordNotFoundException("Study group not found with id: " + id);
        }
        studyGroupRepository.deleteById(id);
    }

    private StudyGroupDto toStudyGroupDto(StudyGroup studyGroup) {
        StudyGroupDto studyGroupDto = new StudyGroupDto();
        BeanUtils.copyProperties(studyGroup, studyGroupDto);
        // Additional mapping for nested entities
        return studyGroupDto;
    }

    private StudyGroup fromStudyGroupDto(StudyGroupDto studyGroupDto) {
        StudyGroup studyGroup = new StudyGroup();
        BeanUtils.copyProperties(studyGroupDto, studyGroup);
        // Additional mapping for nested entities
        return studyGroup;
    }
}