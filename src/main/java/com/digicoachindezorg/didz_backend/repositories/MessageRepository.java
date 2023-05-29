package com.digicoachindezorg.didz_backend.repositories;

import com.digicoachindezorg.didz_backend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByUserId(Long userId);
    List<Message> findByDate(LocalDate date);
    List<Message> findBySenderId(Long senderId);
}