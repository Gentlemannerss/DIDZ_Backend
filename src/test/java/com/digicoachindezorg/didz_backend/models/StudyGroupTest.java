package com.digicoachindezorg.didz_backend.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudyGroupTest {

    @Test
    void getGroupId(){
        //Arrange
        StudyGroup studyGroup = new StudyGroup(1L, "testStudyGroup", null, null, null);
        //Act
        Long response = studyGroup.getGroupId();
        //Assert
        assertEquals(1L, response);
    }

    @Test
    void getGroupName() {
        //Arrange
        StudyGroup studyGroup = new StudyGroup(1L, "testStudyGroup", null, null, null);
        //Act
        String response = studyGroup.getGroupName();
        //Assert
        assertEquals("testStudyGroup", response);
    }

    @Test
    void getProduct() {
        //Arrange
        Product product = new Product(1L, "testProduct", "testDescription", 1.0, null, ProductType.BOOK, null, null);
        StudyGroup studyGroup = new StudyGroup(1L, "testStudyGroup", product, null, null);
        //Act
        Product response = studyGroup.getProduct();
        //Assert
        assertEquals(product, response);
    }

    @Test
    void getUser() {
        // Arrange
        User user = new User(); // Create a user object

        List<User> users = new ArrayList<>();
        users.add(user);

        StudyGroup studyGroup = new StudyGroup(1L, "testStudyGroup", null, users, null);

        // Act
        User response = studyGroup.getUsers().get(0);

        // Assert
        assertEquals(user, response);
    }
}