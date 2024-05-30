package io._2minus.todoapp.service;

import io._2minus.todoapp.CommonResponse;
import io._2minus.todoapp.dto.CommentRequestDTO;
import io._2minus.todoapp.dto.CommentResponseDTO;
import io._2minus.todoapp.entity.Comment;
import io._2minus.todoapp.entity.Todo;
import io._2minus.todoapp.entity.User;
import io._2minus.todoapp.repository.CommentRepository;
import io._2minus.todoapp.repository.TodoRepository;
import io._2minus.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public Comment createComment(Long todoId, User user, CommentRequestDTO dto) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(()->
                 new NullPointerException("잘못된 접근입니다."));

        var comment = dto.toEntity(user, todo);
        commentRepository.save(comment);
        return commentRepository.findById(comment.getCommentId()).orElseThrow(()
        -> new IllegalArgumentException("저장에 오류가 발생했습니다."));
    }

    @Transactional
    public Comment updateComment(Long todoId, User user, Long commentId, CommentRequestDTO dto) {
        Comment comment = checkUserAndGetComment(todoId, user, commentId);
        comment.setContent(dto.getContent());
        return commentRepository.save(comment);

    }

    public void deleteComment(Long todoId, User user, Long commentId) {
        Comment comment = checkUserAndGetComment(todoId, user, commentId);
        commentRepository.delete(comment);
    }


    public Comment checkUserAndGetComment (Long todoId, User user, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new NullPointerException("존재하지 않는 댓글입니다."));

        if (comment.getTodo().getTodoId() != todoId) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        if (!comment.getUser().equals(user)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        return comment;
    }
}
