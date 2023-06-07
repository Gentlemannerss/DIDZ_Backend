package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.input.MessageInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.MessageOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    @PostMapping
    public ResponseEntity<MessageOutputDto> createMessage(@RequestBody MessageInputDto messageDto) {
        MessageOutputDto createdMessage = messageService.createMessage(messageDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MessageOutputDto> updateMessage(@PathVariable("id") Long id,
                                                          @RequestBody MessageInputDto messageDtoToUpdate) throws RecordNotFoundException {
        MessageOutputDto updatedMessage = messageService.updateMessage(id, messageDtoToUpdate);
        return ResponseEntity.ok(updatedMessage);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") Long id) throws RecordNotFoundException {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<MessageOutputDto> getMessage(@PathVariable("id") Long id) throws RecordNotFoundException {
        MessageOutputDto message = messageService.getMessage(id);
        return ResponseEntity.ok(message);
    }
    @GetMapping("/date/{date}")
    public ResponseEntity<List<MessageOutputDto>> getMessagesFromDate(@PathVariable("date") LocalDate date) {
        List<MessageOutputDto> messages = messageService.getMessagesFromDate(date);
        return ResponseEntity.ok(messages);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<MessageOutputDto>> getAllMessagesFromUser(@PathVariable("userId") Long userId) {
        List<MessageOutputDto> messages = messageService.getAllMessagesFromUser(userId);
        return ResponseEntity.ok(messages);
    }
    @GetMapping("/sent/{userId}")
    public ResponseEntity<List<MessageOutputDto>> getSentMessages(@PathVariable("userId") Long userId) {
        List<MessageOutputDto> messages = messageService.getSentMessages(userId);
        return ResponseEntity.ok(messages);
    }
    @GetMapping("/study-group/{studyGroupId}")
    public ResponseEntity<List<MessageOutputDto>> getMessagesFromStudyGroup(@PathVariable("studyGroupId") Long studyGroupId) {
        List<MessageOutputDto> messages = messageService.getMessagesFromStudyGroup(studyGroupId);
        return ResponseEntity.ok(messages);
    }
}