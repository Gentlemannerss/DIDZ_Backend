package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.input.StudyGroupInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.StudyGroupOutputDto;
import com.digicoachindezorg.didz_backend.dtos.output.UserOutputDto;
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
    public ResponseEntity<List<StudyGroupOutputDto>> getAllStudyGroups() {
        List<StudyGroupOutputDto> studyGroups = studyGroupService.getAllStudyGroups();
        return ResponseEntity.ok(studyGroups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyGroupOutputDto> getStudyGroup(@PathVariable Long id) throws RecordNotFoundException {
        StudyGroupOutputDto studyGroup = studyGroupService.getStudyGroup(id);
        return ResponseEntity.ok(studyGroup);
    }

    @PostMapping
    public ResponseEntity<StudyGroupOutputDto> createStudyGroup(@RequestBody StudyGroupInputDto studyGroupInputDto) {
        StudyGroupOutputDto createdStudyGroup = studyGroupService.createStudyGroup(studyGroupInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudyGroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudyGroupOutputDto> updateStudyGroup(@PathVariable Long id, @RequestBody StudyGroupInputDto studyGroupInputDto) throws RecordNotFoundException {
        StudyGroupOutputDto updatedStudyGroup = studyGroupService.updateStudyGroup(id, studyGroupInputDto);
        return ResponseEntity.ok(updatedStudyGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudyGroup(@PathVariable Long id) throws RecordNotFoundException {
        studyGroupService.deleteStudyGroup(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{studyGroupId}/users/{userId}")
    public ResponseEntity<StudyGroupOutputDto> addUserToStudyGroup(@PathVariable Long studyGroupId, @PathVariable Long userId) throws RecordNotFoundException {
        StudyGroupOutputDto updatedStudyGroup = studyGroupService.addUserToStudyGroup(studyGroupId, userId);
        return ResponseEntity.ok(updatedStudyGroup);
    }

    @DeleteMapping("/{studyGroupId}/users/{userId}")
    public ResponseEntity<StudyGroupOutputDto> removeUserFromStudyGroup(@PathVariable Long studyGroupId, @PathVariable Long userId) throws RecordNotFoundException {
        StudyGroupOutputDto updatedStudyGroup = studyGroupService.removeUserFromStudyGroup(studyGroupId, userId);
        return ResponseEntity.ok(updatedStudyGroup);
    }

    @GetMapping("/{studyGroupId}/users")
    public ResponseEntity<List<UserOutputDto>> getStudyGroupUsers(@PathVariable Long studyGroupId) throws RecordNotFoundException {
        List<UserOutputDto> studyGroupUsers = studyGroupService.getStudyGroupUsers(studyGroupId);
        return ResponseEntity.ok(studyGroupUsers);
    }

    @GetMapping("/byProduct/{productId}")
    public ResponseEntity<List<StudyGroupOutputDto>> getStudyGroupsByProduct(@PathVariable Long productId) {
        List<StudyGroupOutputDto> studyGroups = studyGroupService.getStudyGroupsByProduct(productId);
        return ResponseEntity.ok(studyGroups);
    }
}