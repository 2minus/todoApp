package io._2minus.todoapp.service;


import io._2minus.todoapp.controller.TodoRequestDTO;
import io._2minus.todoapp.repository.Todo;
import io._2minus.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 일정 생성
    public Todo createTodo(TodoRequestDTO dto) {
        var newTodo = dto.toEntity();
        return todoRepository.save(newTodo);
    }
}
