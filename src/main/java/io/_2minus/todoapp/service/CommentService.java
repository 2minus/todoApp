package io._2minus.todoapp.service;

import io._2minus.todoapp.dto.CommentRequestDTO;
import io._2minus.todoapp.entity.Comment;
import io._2minus.todoapp.entity.Todo;
import io._2minus.todoapp.entity.User;
import io._2minus.todoapp.repository.CommentRepository;
import io._2minus.todoapp.repository.TodoRepository;
import io._2minus.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public Comment createComment(Long todoId, User user, CommentRequestDTO dto) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(()->
                 new NullPointerException("잘못된 접근입니다."));

        var comment = dto.toEntity(user, todo);
        return commentRepository.save(comment);
    }
}
