package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.input.UserInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.UserOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.BadRequestException;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin //Hoeft niet overal boven, vragen aan sam! Kan geen kwaad.
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
    @GetMapping(value = "/{userId}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok().body(userService.getAuthorities(userId));
    }
    @PostMapping(value = "/{userId}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("userId") Long userId, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(userId, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }
    @DeleteMapping(value = "/{userId}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("userId") Long userId, @PathVariable("authority") String authority) {
        userService.removeAuthority(userId, authority);
        return ResponseEntity.noContent().build();
    }
}