package com.digicoachindezorg.didz_backend.repositories;

import com.digicoachindezorg.didz_backend.models.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {}