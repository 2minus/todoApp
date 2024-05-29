package io._2minus.todoapp.repository;

import io._2minus.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoRepository extends JpaRepository<Todo, Long> {
}
