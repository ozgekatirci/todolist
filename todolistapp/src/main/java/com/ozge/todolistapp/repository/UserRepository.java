package com.ozge.todolistapp.repository;

import com.ozge.todolistapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
}
