package com.digicoachindezorg.didz_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue
    private Long messageId;
    private String messageContent;
    private Boolean isConcept;
    @ManyToOne
    @JoinColumn(name = "sender_id") // Specify the join column name for the sender relationship
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id") // Specify the join column name for the receiver relationship
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "studyGroup_id")
    private StudyGroup studyGroup;
    private LocalDate date;
}
