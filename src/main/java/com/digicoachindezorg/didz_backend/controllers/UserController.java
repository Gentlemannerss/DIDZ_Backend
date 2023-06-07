package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.input.UserInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.UserOutputDto;
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

    @PostMapping
    public ResponseEntity<UserOutputDto> createUser(@RequestBody UserInputDto userInputDto) {
        UserOutputDto createdUser = userService.createUser(userInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserOutputDto> updateUser(@PathVariable Long id, @RequestBody UserInputDto userInputDtoToUpdate) throws RecordNotFoundException {
        UserOutputDto updatedUser = userService.updateUser(id, userInputDtoToUpdate);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws RecordNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDto> getUser(@PathVariable Long id) throws RecordNotFoundException {
        UserOutputDto user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<List<UserOutputDto>> getAllUsers() {
        List<UserOutputDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Hier de vraag of ik deze nodig heb, omdat een invoice al gekoppeld wordt aan een user.
    @PutMapping("/{userId}/invoices/{invoiceId}")
    public ResponseEntity<Void> assignUserToInvoice(@PathVariable Long userId, @PathVariable Long invoiceId) throws RecordNotFoundException {
        userService.assignUserToInvoice(userId, invoiceId);
        return ResponseEntity.ok().build();
    }
}