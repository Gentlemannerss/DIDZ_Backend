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
@Table(name = "Messages")
public class Message {
    @Id
    @GeneratedValue
    private String messageId;
    private Boolean isConcept;
    @ManyToOne
    private Message message;
    @ManyToOne
    private User user;
    @ManyToOne
    private StudyGroup studyGroup;
    private LocalDate date;
}
