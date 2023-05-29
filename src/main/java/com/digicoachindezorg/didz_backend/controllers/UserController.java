package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.UserDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) throws RecordNotFoundException {
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDtoToUpdate) throws RecordNotFoundException {
        UserDto updatedUser = userService.updateUser(id, userDtoToUpdate);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws RecordNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /*@PostMapping("/{userId}/studyGroups/{studyGroupId}")
    public ResponseEntity<Void> assignUserToStudyGroup(@PathVariable Long userId, @PathVariable Long studyGroupId) throws RecordNotFoundException {
        userService.assignUserToStudyGroup(userId, studyGroupId);
        return ResponseEntity.ok().build();
    }*/

    @PostMapping("/{userId}/invoices/{invoiceId}")
    public ResponseEntity<Void> assignUserToInvoice(@PathVariable Long userId, @PathVariable Long invoiceId) throws RecordNotFoundException {
        userService.assignUserToInvoice(userId, invoiceId);
        return ResponseEntity.ok().build();
    }
}