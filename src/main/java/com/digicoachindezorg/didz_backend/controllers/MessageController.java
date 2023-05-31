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
        return ResponseEntity.ok(message);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<MessageDto>> getAllMessagesFromUser(@PathVariable("userId") Long userId) {
        List<MessageDto> messages = messageService.getAllMessagesFromUser(userId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<MessageDto>> getMessagesFromDate(@PathVariable("date") LocalDate date) {
        List<MessageDto> messages = messageService.getMessagesFromDate(date);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/sent/{userId}")
    public ResponseEntity<List<MessageDto>> getSentMessages(@PathVariable("userId") Long userId) {
        List<MessageDto> messages = messageService.getSentMessages(userId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto) {
        MessageDto createdMessage = messageService.createMessage(messageDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> updateMessage(@PathVariable("id") Long id,
                                                    @RequestBody MessageDto messageDtoToUpdate) throws RecordNotFoundException {
        MessageDto updatedMessage = messageService.updateMessage(id, messageDtoToUpdate);
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") Long id) throws RecordNotFoundException {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/send/user/{senderId}/{receiverId}")
    public ResponseEntity<MessageDto> sendMessageToUser(@PathVariable("senderId") Long senderId,
                                                        @PathVariable("receiverId") Long receiverId,
                                                        @RequestBody MessageDto messageDto) throws RecordNotFoundException {
        MessageDto sentMessage = messageService.sendMessageToUser(senderId, receiverId, messageDto);
        return ResponseEntity.ok(sentMessage);
    }

    @PostMapping("/send/message-board/{studyGroupId}/{senderId}")
    public ResponseEntity<MessageDto> sendMessageToMessageBoard(@PathVariable("studyGroupId") Long studyGroupId,
                                                                @PathVariable("senderId") Long senderId,
                                                                @RequestBody MessageDto messageDto) {
        try {
            MessageDto sentMessage = messageService.sendMessageToMessageBoard(senderId, studyGroupId, messageDto);
            return ResponseEntity.ok(sentMessage);
        } catch (RecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/study-group/{studyGroupId}")
    public ResponseEntity<List<MessageDto>> getMessagesFromStudyGroup(@PathVariable("studyGroupId") Long studyGroupId) {
        List<MessageDto> messages = messageService.getMessagesFromStudyGroup(studyGroupId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/study-group/{studyGroupId}")
    public ResponseEntity<MessageDto> createMessageInStudyGroup(@PathVariable("studyGroupId") Long studyGroupId,
                                                                @RequestBody MessageDto messageDto) throws RecordNotFoundException {
        MessageDto createdMessage = messageService.createMessageInStudyGroup(studyGroupId, messageDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
    }

    @DeleteMapping("/study-group/{studyGroupId}/{messageId}")
    public ResponseEntity<Void> deleteMessageInStudyGroup(@PathVariable("studyGroupId") Long studyGroupId,
                                                          @PathVariable("messageId") Long messageId) throws RecordNotFoundException {
        messageService.deleteMessageInStudyGroup(studyGroupId, messageId);
        return ResponseEntity.noContent().build();
    }
}