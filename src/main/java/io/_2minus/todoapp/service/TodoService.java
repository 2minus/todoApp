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
    // 일정 전체 조회
    public List<Todo> getTodos() {
        return todoRepository.findAll(Sort.by("createdAt").descending());
    }
    // 일정 수정
    public Todo updateTodo(Long todoId, TodoRequestDTO dto) {
        Todo todo = getTodo(todoId);

        // 비밀번호 검증
        if(todo.getPassword() != null
        && !todo.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("Passwords don't match");
        }

        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());
        todo.setUserName(dto.getUserName());

        return todoRepository.save(todo);
    }

}
