package io._2minus.todoapp.controller;

import io._2minus.todoapp.CommonResponse;
import io._2minus.todoapp.dto.CommentRequestDTO;
import io._2minus.todoapp.dto.CommentResponseDTO;
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

}
