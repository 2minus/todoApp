package io._2minus.todoapp.service;


import io._2minus.todoapp.controller.TodoRequestDTO;
import io._2minus.todoapp.repository.Todo;
import io._2minus.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 일정 생성
    public Todo createTodo(TodoRequestDTO dto) {
        var newTodo = dto.toEntity();
        return todoRepository.save(newTodo);
    }
    // 일정 단건 조회
    public Todo getTodo(Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll(Sort.by("createdAt").descending());
    }
}
