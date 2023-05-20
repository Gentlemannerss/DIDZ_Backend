package com.digicoachindezorg.didz_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Study Groups")
public class StudyGroup {
    @Id
    @GeneratedValue
    private String groupId;
    @OneToOne
    private Product product;
    @ManyToMany
    private List<User> users;
    @OneToMany
    private List<Message> pinboardMessages;
}
