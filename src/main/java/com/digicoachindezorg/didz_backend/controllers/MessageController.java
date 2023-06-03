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

    @GetMapping("/{id}")
    public ResponseEntity<MessageOutputDto> getMessage(@PathVariable("id") Long id) throws RecordNotFoundException {
        MessageOutputDto message = messageService.getMessage(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<MessageOutputDto>> getAllMessagesFromUser(@PathVariable("userId") Long userId) {
        List<MessageOutputDto> messages = messageService.getAllMessagesFromUser(userId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<MessageOutputDto>> getMessagesFromDate(@PathVariable("date") LocalDate date) {
        List<MessageOutputDto> messages = messageService.getMessagesFromDate(date);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/sent/{userId}")
    public ResponseEntity<List<MessageOutputDto>> getSentMessages(@PathVariable("userId") Long userId) {
        List<MessageOutputDto> messages = messageService.getSentMessages(userId);
        return ResponseEntity.ok(messages);
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

    @PostMapping("/send/user/{senderId}/{receiverId}")
    public ResponseEntity<MessageOutputDto> sendMessageToUser(@PathVariable("senderId") Long senderId,
                                                              @PathVariable("receiverId") Long receiverId,
                                                              @RequestBody MessageInputDto messageDto) throws RecordNotFoundException {
        MessageOutputDto sentMessage = messageService.sendMessageToUser(senderId, receiverId, messageDto);
        return ResponseEntity.ok(sentMessage);
    }

    @PostMapping("/send/message-board/{studyGroupId}/{senderId}")
    public ResponseEntity<MessageOutputDto> sendMessageToMessageBoard(@PathVariable("studyGroupId") Long studyGroupId,
                                                                      @PathVariable("senderId") Long senderId,
                                                                      @RequestBody MessageInputDto messageDto) {
        try {
            MessageOutputDto sentMessage = messageService.sendMessageToMessageBoard(senderId, studyGroupId, messageDto);
            return ResponseEntity.ok(sentMessage);
        } catch (RecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/study-group/{studyGroupId}")
    public ResponseEntity<List<MessageOutputDto>> getMessagesFromStudyGroup(@PathVariable("studyGroupId") Long studyGroupId) {
        List<MessageOutputDto> messages = messageService.getMessagesFromStudyGroup(studyGroupId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/study-group/{studyGroupId}")
    public ResponseEntity<MessageOutputDto> createMessageInStudyGroup(@PathVariable("studyGroupId") Long studyGroupId,
                                                                      @RequestBody MessageInputDto messageDto) throws RecordNotFoundException {
        MessageOutputDto createdMessage = messageService.createMessageInStudyGroup(studyGroupId, messageDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
    }

    @DeleteMapping("/study-group/{studyGroupId}/{messageId}")
    public ResponseEntity<Void> deleteMessageInStudyGroup(@PathVariable("studyGroupId") Long studyGroupId,
                                                          @PathVariable("messageId") Long messageId) throws RecordNotFoundException {
        messageService.deleteMessageInStudyGroup(studyGroupId, messageId);
        return ResponseEntity.noContent().build();
    }
}