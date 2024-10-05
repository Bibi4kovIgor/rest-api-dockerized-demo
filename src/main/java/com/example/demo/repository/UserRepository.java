package com.example.demo.repository;

import com.example.demo.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Transactional
    @NonNull
    <S extends User> S save(@NonNull S user);

    @NonNull
    List<User>findAll();

    User findFirstByNameContaining(String name); // SELECT * FROM User a WHERE a.name LIKE '%name%' LIMIT 1
}
