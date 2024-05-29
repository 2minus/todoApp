package io._2minus.todoapp.dto;

import io._2minus.todoapp.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();

    }
}
