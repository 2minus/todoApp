package io._2minus.todoapp.dto;

import io._2minus.todoapp.entity.Comment;
import io._2minus.todoapp.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private Long todoId;
    private String userName;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.todoId = comment.getTodo().getTodoId();
        this.userName = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();

    }
}
