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

    public MessageOutputDto sendMessageToUser(MessageInputDto messageDto) throws RecordNotFoundException {
        Message message = transferMessageInputDtoToMessage(messageDto);
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

    public MessageOutputDto sendMessageToMessageBoard(MessageInputDto messageDto) throws RecordNotFoundException {
        Message message = transferMessageInputDtoToMessage(messageDto);
        Message createdMessage = messageRepository.save(message);
        return transferMessageToMessageOutputDto(createdMessage);
    }

    private MessageOutputDto transferMessageToMessageOutputDto(Message message) {
        MessageOutputDto messageDto = new MessageOutputDto();
        messageDto.setMessageId(message.getMessageId());
        messageDto.setStudyGroup(message.getStudyGroup());
        messageDto.setUser(message.getSender());
        messageDto.setIsConcept(message.getIsConcept());
        User receiver = message.getReceiver();
        if (receiver != null) {
            messageDto.setReceiverEmail(receiver.getEMail());
        }
        /*messageDto.setReceiverEmail(message.getReceiver().getEMail()); //Email nodig omdat je een string vraagt voor receiver.*/
        /*messageDto.setParentMessage(message.getMessage());*/
        return messageDto;
    }

    private Message transferMessageInputDtoToMessage(MessageInputDto messageDto) {
        Message message = new Message();
        if (messageDto.getStudyGroupId()!=null) {
            StudyGroup studyGroup = studyGroupRepository.findById(messageDto.getStudyGroupId())
                    .orElseThrow(() -> new RecordNotFoundException("Study Group not found with id: " + messageDto.getStudyGroupId()));
            message.setStudyGroup(studyGroup);
        }
        /*if (messageDto.getParentMessage()!=null) {
            message.setParentMessage(messageDto.getParentMessage()); //ParentMessage wordt gebruikt voor de StudyGroyp denk ik.... Geen idee, wat moet ik hiermee doen?
        }*/
        if (messageDto.getSenderId()!=null) {
            User user = userRepository.findById(messageDto.getSenderId())
                    .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + messageDto.getSenderId()));
            message.setSender(user);
        }
        if (messageDto.getIsConcept()!=null) {
            message.setIsConcept(messageDto.getIsConcept());
        }
        if (messageDto.getReceiverEmail()!=null) {
            User user = userRepository.findByeMail(messageDto.getReceiverEmail());
            message.setReceiver(user);
        }
        return message;
    }

    private Message updateMessageInputDtoToMessage(MessageInputDto messageDto, Message message) {
        if (messageDto.getStudyGroupId()!=null) {
            StudyGroup studyGroup = studyGroupRepository.findById(messageDto.getStudyGroupId())
                    .orElseThrow(() -> new RecordNotFoundException("Study Group not found with id: " + messageDto.getStudyGroupId()));
            message.setStudyGroup(studyGroup);
        }
        /*if (messageDto.getParentMessage()!=null) {
            message.setParentMessage(messageDto.getParentMessage()); //ParentMessage wordt gebruikt voor de StudyGroyp denk ik.... Geen idee, wat moet ik hiermee doen?
        }*/
        if (messageDto.getSenderId()!=null) {
            User user = userRepository.findById(messageDto.getSenderId())
                    .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + messageDto.getSenderId()));
            message.setSender(user);
        }
        if (messageDto.getIsConcept()!=null) {
            message.setIsConcept(messageDto.getIsConcept());
        }
        if (messageDto.getReceiverEmail()!=null) {
            User user = userRepository.findByeMail(messageDto.getReceiverEmail());
            message.setReceiver(user); //De receiver wordt door een gebruiker zelf gekozen door een email in te vullen Hoe werkt dit dan?
        }
        return message;
    }
}