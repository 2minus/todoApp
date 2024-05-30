package io._2minus.todoapp.dto;


import io._2minus.todoapp.entity.Todo;
import io._2minus.todoapp.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequestDTO {
//    private User user;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    public Todo toEntity(User user) {
        return Todo.builder()
                .user(user)
                .title(title)
                .content(content)
                .build();
    }
}
