package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.MessageDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Message;
import com.digicoachindezorg.didz_backend.repositories.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageDto getMessage(Long id) throws RecordNotFoundException {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Message not found with id: " + id));
        return toMessageDto(message);
    }

    public List<MessageDto> getAllMessagesFromUser(Long userId) {
        List<Message> messages = messageRepository.findByUserId(userId);
        return messages.stream()
                .map(this::toMessageDto)
                .collect(Collectors.toList());
    }

    public List<MessageDto> getMessagesFromDate(LocalDate date) {
        List<Message> messages = messageRepository.findByDate(date);
        return messages.stream()
                .map(this::toMessageDto)
                .collect(Collectors.toList());
    }

    public List<MessageDto> getSentMessages(Long userId) {
        List<Message> messages = messageRepository.findBySenderId(userId);
        return messages.stream()
                .map(this::toMessageDto)
                .collect(Collectors.toList());
    }

    public MessageDto createMessage(MessageDto messageDto) {
        Message message = fromMessageDto(messageDto);
        Message createdMessage = messageRepository.save(message);
        return toMessageDto(createdMessage);
    }

    public MessageDto updateMessage(Long id, MessageDto messageDtoToUpdate) throws RecordNotFoundException {
        Message existingMessage = messageRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Message not found with id: " + id));

        // Update the fields of the existing message
        BeanUtils.copyProperties(messageDtoToUpdate, existingMessage);

        Message updatedMessage = messageRepository.save(existingMessage);
        return toMessageDto(updatedMessage);
    }

    public void deleteMessage(Long id) throws RecordNotFoundException {
        if (!messageRepository.existsById(id)) {
            throw new RecordNotFoundException("Message not found with id: " + id);
        }
        messageRepository.deleteById(id);
    }

    public MessageDto sendMessageToUser(Long senderId, Long receiverId, MessageDto messageDto) throws RecordNotFoundException {
        Message message = fromMessageDto(messageDto);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        Message createdMessage = messageRepository.save(message);
        return toMessageDto(createdMessage);
    }

    public MessageDto sendMessageToMessageBoard(Long senderId, MessageDto messageDto) {
        Message message = fromMessageDto(messageDto);
        message.setSenderId(senderId);
        // Additional logic for sending a message to the message board
        Message createdMessage = messageRepository.save(message);
        return toMessageDto(createdMessage);
    }

    private MessageDto toMessageDto(Message message) {
        MessageDto messageDto = new MessageDto();
        BeanUtils.copyProperties(message, messageDto);
        // Additional mapping for nested entities
        return messageDto;
    }

    private Message fromMessageDto(MessageDto messageDto) {
        Message message = new Message();
        BeanUtils.copyProperties(messageDto, message);
        // Additional mapping for nested entities
        return message;
    }
}