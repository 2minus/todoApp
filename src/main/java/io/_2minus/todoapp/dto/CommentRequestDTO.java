package io._2minus.todoapp.dto;

import io._2minus.todoapp.entity.Comment;
import io._2minus.todoapp.entity.Todo;
import io._2minus.todoapp.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {

    private User user;
    private Todo todo;
    @NotBlank
    private String content;

    public Comment toEntity(User user, Todo todo){
        return Comment.builder()
                .user(user)
                .todo(todo)
                .content(content)
                .build();
    }
}
