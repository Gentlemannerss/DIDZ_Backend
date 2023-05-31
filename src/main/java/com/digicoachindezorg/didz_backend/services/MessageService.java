package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.MessageDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Message;
import com.digicoachindezorg.didz_backend.models.StudyGroup;
import com.digicoachindezorg.didz_backend.models.User;
import com.digicoachindezorg.didz_backend.repositories.MessageRepository;
import com.digicoachindezorg.didz_backend.repositories.StudyGroupRepository;
import com.digicoachindezorg.didz_backend.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, StudyGroupRepository studyGroupRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.userRepository = userRepository;
    }

    public MessageDto getMessage(Long id) throws RecordNotFoundException {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Message not found with id: " + id));
        return toMessageDto(message);
    }

    public List<MessageDto> getAllMessagesFromUser(Long userId) {
        List<Message> messages = messageRepository.findBySender_Id(userId);
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
        List<Message> messages = messageRepository.findBySender_Id(userId);
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
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + senderId));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + receiverId));
        message.setSender(sender);
        message.setReceiver(receiver);

        Message createdMessage = messageRepository.save(message);
        return toMessageDto(createdMessage);
    }

    public List<MessageDto> getMessagesFromStudyGroup(Long studyGroupId) {
        List<Message> messages = messageRepository.findByStudyGroup_GroupId(studyGroupId);
        return messages.stream()
                .map(this::toMessageDto)
                .collect(Collectors.toList());
    }

    public MessageDto createMessageInStudyGroup(Long studyGroupId, MessageDto messageDto) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + studyGroupId));

        Message message = fromMessageDto(messageDto);
        message.setStudyGroup(studyGroup);

        Message createdMessage = messageRepository.save(message);
        return toMessageDto(createdMessage);
    }

    public void deleteMessageInStudyGroup(Long studyGroupId, Long messageId) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + studyGroupId));

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RecordNotFoundException("Message not found with id: " + messageId));

        if (message.getStudyGroup() != null && message.getStudyGroup().equals(studyGroup)) {
            studyGroup.getMessageBoard().remove(message);
            messageRepository.delete(message);
        } else {
            throw new RecordNotFoundException("Message not found in the specified study group");
        }
    }

    public MessageDto sendMessageToMessageBoard(Long senderId, Long studyGroupId, MessageDto messageDto) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + studyGroupId));

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + senderId));

        Message message = fromMessageDto(messageDto);
        message.setSender(sender);
        message.setStudyGroup(studyGroup);

        Message createdMessage = messageRepository.save(message);
        return toMessageDto(createdMessage);
    }

    private MessageDto toMessageDto(Message message) { /*Message to MessageDTO*/
        MessageDto messageDto = new MessageDto();
        BeanUtils.copyProperties(message, messageDto);
        return messageDto;
    }

    private Message fromMessageDto(MessageDto messageDto) { /*MessageDTO to Message*/
        Message message = new Message();
        BeanUtils.copyProperties(messageDto, message);
        return message;
    }
}