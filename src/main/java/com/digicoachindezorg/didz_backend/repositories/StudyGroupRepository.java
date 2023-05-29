package com.digicoachindezorg.didz_backend.repositories;

import com.digicoachindezorg.didz_backend.models.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {
    List<StudyGroup> findByProduct_ProductId(Long productId);
}