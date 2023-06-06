package com.digicoachindezorg.didz_backend.repositories;

import com.digicoachindezorg.didz_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByeMail(String email);
}