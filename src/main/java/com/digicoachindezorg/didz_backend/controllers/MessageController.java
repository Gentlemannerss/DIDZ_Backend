package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.MessageDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMessage(@PathVariable("id") Long id) throws RecordNotFoundException {
        MessageDto message = messageService.getMessage(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<MessageDto>> getAllMessagesFromUser(@PathVariable("userId") Long userId) {
        List<MessageDto> messages = messageService.getAllMessagesFromUser(userId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<MessageDto>> getMessagesFromDate(@PathVariable("date") LocalDate date) {
        List<MessageDto> messages = messageService.getMessagesFromDate(date);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/sent/{userId}")
    public ResponseEntity<List<MessageDto>> getSentMessages(@PathVariable("userId") Long userId) {
        List<MessageDto> messages = messageService.getSentMessages(userId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto) {
        MessageDto createdMessage = messageService.createMessage(messageDto);
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> updateMessage(@PathVariable("id") Long id, @RequestBody MessageDto messageDtoToUpdate) throws RecordNotFoundException {
        MessageDto updatedMessage = messageService.updateMessage(id, messageDtoToUpdate);
        return new ResponseEntity<>(updatedMessage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") Long id) throws RecordNotFoundException {
        messageService.deleteMessage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/send/user/{senderId}/{receiverId}")
    public ResponseEntity<MessageDto> sendMessageToUser(@PathVariable("senderId") Long senderId, @PathVariable("receiverId") Long receiverId, @RequestBody MessageDto messageDto) throws RecordNotFoundException {
        MessageDto sentMessage = messageService.sendMessageToUser(senderId, receiverId, messageDto);
        return new ResponseEntity<>(sentMessage, HttpStatus.OK);
    }

    @PostMapping("/send/message-board/{senderId}")
    public ResponseEntity<MessageDto> sendMessageToMessageBoard(@PathVariable("senderId") Long senderId, @RequestBody MessageDto messageDto) {
        MessageDto sentMessage = messageService.sendMessageToMessageBoard(senderId, messageDto);
        return new ResponseEntity<>(sentMessage, HttpStatus.OK);
    }
}
