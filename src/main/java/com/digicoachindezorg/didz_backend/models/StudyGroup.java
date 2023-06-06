package com.digicoachindezorg.didz_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "studyGroups")
public class StudyGroup {
    @Id
    @GeneratedValue
    private Long groupId;
    private String groupName;
    @OneToOne
    private Product product;
    @ManyToMany
    @JoinTable(
            name = "studygroup_user",
            joinColumns = @JoinColumn(name = "studygroup_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
    @OneToMany(mappedBy = "studyGroup")
    private List<Message> MessageBoard = new ArrayList<>();
}
