package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.StudyGroupDto;
import com.digicoachindezorg.didz_backend.dtos.UserDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.services.StudyGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studyGroups")
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    public StudyGroupController(StudyGroupService studyGroupService) {
        this.studyGroupService = studyGroupService;
    }

    @GetMapping
    public ResponseEntity<List<StudyGroupDto>> getAllStudyGroups() {
        List<StudyGroupDto> studyGroups = studyGroupService.getAllStudyGroups();
        return ResponseEntity.ok(studyGroups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyGroupDto> getStudyGroup(@PathVariable Long id) throws RecordNotFoundException {
        StudyGroupDto studyGroup = studyGroupService.getStudyGroup(id);
        return ResponseEntity.ok(studyGroup);
    }

    @PostMapping
    public ResponseEntity<StudyGroupDto> createStudyGroup(@RequestBody StudyGroupDto studyGroupDto) {
        StudyGroupDto createdStudyGroup = studyGroupService.createStudyGroup(studyGroupDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudyGroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudyGroupDto> updateStudyGroup(@PathVariable Long id, @RequestBody StudyGroupDto studyGroupDto) throws RecordNotFoundException {
        StudyGroupDto updatedStudyGroup = studyGroupService.updateStudyGroup(id, studyGroupDto);
        return ResponseEntity.ok(updatedStudyGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudyGroup(@PathVariable Long id) throws RecordNotFoundException {
        studyGroupService.deleteStudyGroup(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{studyGroupId}/users/{userId}")
    public ResponseEntity<StudyGroupDto> addUserToStudyGroup(@PathVariable Long studyGroupId, @PathVariable Long userId) throws RecordNotFoundException {
        StudyGroupDto updatedStudyGroup = studyGroupService.addUserToStudyGroup(studyGroupId, userId);
        return ResponseEntity.ok(updatedStudyGroup);
    }

    @DeleteMapping("/{studyGroupId}/users/{userId}")
    public ResponseEntity<StudyGroupDto> removeUserFromStudyGroup(@PathVariable Long studyGroupId, @PathVariable Long userId) throws RecordNotFoundException {
        StudyGroupDto updatedStudyGroup = studyGroupService.removeUserFromStudyGroup(studyGroupId, userId);
        return ResponseEntity.ok(updatedStudyGroup);
    }

    @GetMapping("/{studyGroupId}/users")
    public ResponseEntity<List<UserDto>> getStudyGroupUsers(@PathVariable Long studyGroupId) throws RecordNotFoundException {
        List<UserDto> studyGroupUsers = studyGroupService.getStudyGroupUsers(studyGroupId);
        return ResponseEntity.ok(studyGroupUsers);
    }

    @GetMapping("/byProduct/{productId}")
    public ResponseEntity<List<StudyGroupDto>> getStudyGroupsByProduct(@PathVariable Long productId) {
        List<StudyGroupDto> studyGroups = studyGroupService.getStudyGroupsByProduct(productId);
        return ResponseEntity.ok(studyGroups);
    }
}
