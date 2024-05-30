package io._2minus.todoapp.service;


import io._2minus.todoapp.dto.TodoRequestDTO;
import io._2minus.todoapp.entity.Todo;
import io._2minus.todoapp.entity.User;
import io._2minus.todoapp.repository.TodoRepository;
import io._2minus.todoapp.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 일정 생성
    public Todo createTodo(TodoRequestDTO dto, User user) {
        var newTodo = dto.toEntity(user);
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
    @Transactional
    public Todo updateTodo(Long todoId, User user, TodoRequestDTO dto) {
        Todo todo = checkUserAndGetTodo(todoId, user);

        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());
        todo.setUserName(user.getUsername());

        return todoRepository.save(todo);
    }
    // 일정 삭제
    public void deleteTodo(Long todoId, User user) {
        Todo todo = checkUserAndGetTodo(todoId, user);
        todoRepository.delete(todo);
    }

    private Todo checkUserAndGetTodo(Long todoId, User user) {
        Todo todo = getTodo(todoId);
        // 비밀번호 검증
        if(todo.getUser() != null
                && !todo.getUser().equals(user)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        return todo;
    }
}
