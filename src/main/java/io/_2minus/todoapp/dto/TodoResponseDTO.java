package io._2minus.todoapp.dto;

import io._2minus.todoapp.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoResponseDTO {
    private Long todoId;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdAt;

    public TodoResponseDTO(Todo todo) {
        this.todoId = todo.getTodoId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.userName = todo.getUser().getUsername();
        this.createdAt = todo.getCreatedAt();
    }
}
