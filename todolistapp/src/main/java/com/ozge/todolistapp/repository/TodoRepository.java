package com.ozge.todolistapp.repository;

import com.ozge.todolistapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
