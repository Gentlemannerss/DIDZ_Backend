package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.input.MessageInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.MessageOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Message;
import com.digicoachindezorg.didz_backend.models.StudyGroup;
import com.digicoachindezorg.didz_backend.models.User;
import com.digicoachindezorg.didz_backend.repositories.MessageRepository;
import com.digicoachindezorg.didz_backend.repositories.StudyGroupRepository;
import com.digicoachindezorg.didz_backend.repositories.UserRepository;
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

    public MessageOutputDto getMessage(Long id) throws RecordNotFoundException {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Message not found with id: " + id));
        return transferMessageToMessageOutputDto(message);
    }

    public List<MessageOutputDto> getAllMessagesFromUser(Long userId) {
        List<Message> messages = messageRepository.findBySender_Id(userId);
        return messages.stream()
                .map(this::transferMessageToMessageOutputDto)
                .collect(Collectors.toList());
    }

    public List<MessageOutputDto> getMessagesFromDate(LocalDate date) {
        List<Message> messages = messageRepository.findByDate(date);
        return messages.stream()
                .map(this::transferMessageToMessageOutputDto)
                .collect(Collectors.toList());
    }

    public List<MessageOutputDto> getSentMessages(Long userId) {
        List<Message> messages = messageRepository.findBySender_Id(userId);
        return messages.stream()
                .map(this::transferMessageToMessageOutputDto)
                .collect(Collectors.toList());
    }

    public MessageOutputDto createMessage(MessageInputDto messageDto) {
        Message message = transferMessageInputDtoToMessage(messageDto);
        Message createdMessage = messageRepository.save(message);
        return transferMessageToMessageOutputDto(createdMessage);
    }

    public MessageOutputDto updateMessage(Long id, MessageInputDto messageDtoToUpdate) throws RecordNotFoundException {
        Message existingMessage = messageRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Message not found with id: " + id));

        // Update the fields of the existing message
        Message updatedMessage = updateMessageInputDtoToMessage(messageDtoToUpdate, existingMessage);

        Message savedMessage = messageRepository.save(updatedMessage);
        return transferMessageToMessageOutputDto(savedMessage);
    }

    public void deleteMessage(Long id) throws RecordNotFoundException {
        if (!messageRepository.existsById(id)) {
            throw new RecordNotFoundException("Message not found with id: " + id);
        }
        messageRepository.deleteById(id);
    }

    public MessageOutputDto sendMessageToUser(Long senderId, Long receiverId, MessageInputDto messageDto) throws RecordNotFoundException {
        Message message = transferMessageInputDtoToMessage(messageDto);
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + senderId));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + receiverId));
        message.setSender(sender);
        message.setReceiver(receiver);

        Message createdMessage = messageRepository.save(message);
        return transferMessageToMessageOutputDto(createdMessage);
    }

    public List<MessageOutputDto> getMessagesFromStudyGroup(Long studyGroupId) {
        List<Message> messages = messageRepository.findByStudyGroup_GroupId(studyGroupId);
        return messages.stream()
                .map(this::transferMessageToMessageOutputDto)
                .collect(Collectors.toList());
    }

    public MessageOutputDto createMessageInStudyGroup(Long studyGroupId, MessageInputDto messageDto) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + studyGroupId));

        Message message = transferMessageInputDtoToMessage(messageDto);
        message.setStudyGroup(studyGroup);

        Message createdMessage = messageRepository.save(message);
        return transferMessageToMessageOutputDto(createdMessage);
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

    public MessageOutputDto sendMessageToMessageBoard(Long senderId, Long studyGroupId, MessageInputDto messageDto) throws RecordNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new RecordNotFoundException("Study group not found with id: " + studyGroupId));

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + senderId));

        Message message = transferMessageInputDtoToMessage(messageDto);
        message.setSender(sender);
        message.setStudyGroup(studyGroup);

        Message createdMessage = messageRepository.save(message);
        return transferMessageToMessageOutputDto(createdMessage);
    }

    private MessageOutputDto transferMessageToMessageOutputDto(Message message) {
        MessageOutputDto messageDto = new MessageOutputDto();
        messageDto.setMessageId(message.getMessageId());
        messageDto.setStudyGroup(message.getStudyGroup());
        messageDto.setUser(message.getSender());
        messageDto.setIsConcept(message.getIsConcept());
        messageDto.setReceiverEmail(message.getReceiver().getEMail()); //Email nodig omdat je een string vraagt voor receiver.
        messageDto.setParentMessage(message.getMessage());
        return messageDto;
    }

    private Message transferMessageInputDtoToMessage(MessageInputDto messageDto) {
        Message message = new Message();
        if (messageDto.getStudyGroup()!=null) {
            message.setStudyGroup(messageDto.getStudyGroup());
        }
        if (messageDto.getParentMessage()!=null) {
            message.setParentMessage(messageDto.getParentMessage()); //ParentMessage wordt gebruikt voor de StudyGroyp denk ik....
        }
        if (messageDto.getSender()!=null) {
            message.setSender(messageDto.getSender());
        }
        if (messageDto.getIsConcept()!=null) {
            message.setIsConcept(messageDto.getIsConcept());
        }
        if (messageDto.getReceiverEmail()!=null) {
            message.setReceiverEmail(messageDto.getReceiverEmail()); //De receiver wordt door een gebruiker zelf gekozen. Hoe werkt dit dan?
        }
        return message;
    }

    private Message updateMessageInputDtoToMessage(MessageInputDto messageDto, Message message) {
        if (messageDto.getStudyGroup()!=null) {
            message.setStudyGroup(messageDto.getStudyGroup());
        }
        if (messageDto.getParentMessage()!=null) {
            message.setParentMessage(messageDto.getParentMessage());
        }
        if (messageDto.getSender()!=null) {
            message.setSender(messageDto.getSender());
        }
        if (messageDto.getIsConcept()!=null) {
            message.setIsConcept(messageDto.getIsConcept());
        }
        if (messageDto.getReceiverEmail()!=null) {
            message.setReceiverEmail(messageDto.getReceiverEmail());
        }
        return message;
    }
}