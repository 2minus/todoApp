package io._2minus.todoapp.controller;

import io._2minus.todoapp.CommonResponse;
import io._2minus.todoapp.dto.CommentRequestDTO;
import io._2minus.todoapp.dto.CommentResponseDTO;
import io._2minus.todoapp.dto.TodoResponseDTO;
import io._2minus.todoapp.entity.Comment;
import io._2minus.todoapp.entity.User;
import io._2minus.todoapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1.0/todo")
@RestController
@RequiredArgsConstructor
public class CommentController {

    public final CommentService commentService;

    @PostMapping("/{todoId}/comments")
    public ResponseEntity<CommonResponse<CommentResponseDTO>> postComment(@PathVariable Long todoId, User user, @RequestBody CommentRequestDTO dto) {
        Comment comment = commentService.createComment(todoId, user, dto);
        CommentResponseDTO response = new CommentResponseDTO(comment);
        return ResponseEntity.ok().body(CommonResponse.<CommentResponseDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("생성이 완료되었습니다.")
                .data(response)
                .build());
    }

    @PutMapping("/{todoId}/comments/{commentId}")
    public ResponseEntity<CommonResponse<CommentResponseDTO>> putComment(@PathVariable Long todoId, User user, @PathVariable Long commentId, @RequestBody CommentRequestDTO dto) {
        Comment comment = commentService.updateComment(todoId, user, commentId, dto);
        CommentResponseDTO response = new CommentResponseDTO(comment);
        return ResponseEntity.ok().body(CommonResponse.<CommentResponseDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("수정 내용 : " + comment.getContent())
                .data(response)
                .build());
    }

    @DeleteMapping("/{todoId}/comments/{commentId}")
    public ResponseEntity<CommonResponse> deleteComment(@PathVariable Long todoId, User user, @PathVariable Long commentId, @RequestBody CommentRequestDTO dto) {
        commentService.deleteComment(todoId, user, commentId, dto);
        return ResponseEntity.ok().body(CommonResponse.<CommentResponseDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("삭제가 완료되었습니다.")
                .build());
    }

}
