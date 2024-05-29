package io._2minus.todoapp.controller;


import io._2minus.todoapp.CommonResponse;
import io._2minus.todoapp.dto.TodoRequestDTO;
import io._2minus.todoapp.dto.TodoResponseDTO;
import io._2minus.todoapp.entity.Todo;
import io._2minus.todoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1.0/todo")
@RestController
@RequiredArgsConstructor
public class TodoController {

    public final TodoService todoService;

    @PostMapping
    public ResponseEntity<CommonResponse<TodoResponseDTO>> postTodo(@RequestBody TodoRequestDTO dto) {
        Todo todo = todoService.createTodo(dto);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("생성이 완료되었습니다.")
                .data(response)
                .build());
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDTO>> getTodo(@PathVariable Long todoId) {
        Todo todo = todoService.getTodo(todoId);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("조회가 완료되었습니다.")
                .data(response)
                .build());
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TodoResponseDTO>>> getTodos() {
        List<Todo> todos = todoService.getTodos();
        List<TodoResponseDTO> response = todos.stream()
                .map(TodoResponseDTO::new)
                .toList();
        return ResponseEntity.ok().body(CommonResponse.<List<TodoResponseDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("조회가 완료되었습니다.")
                .data(response)
                .build());
    }

    @PutMapping("/{todoId}")
    public  ResponseEntity<CommonResponse<TodoResponseDTO>> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
        Todo todo = todoService.updateTodo(todoId, dto);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("수정이 완료되었습니다.")
                .data(response)
                .build());
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<CommonResponse> deleteTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
        todoService.deleteTodo(todoId,dto.getPassword());
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("삭제가 완료되었습니다.")
                .build());
    }
}
