package io.github.eroshenkoam.heisenbug.web.rest;

import io.github.eroshenkoam.heisenbug.security.AuthoritiesConstants;
import io.github.eroshenkoam.heisenbug.service.TodoService;
import io.github.eroshenkoam.heisenbug.service.dto.TodoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TodoResource {

    private TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<TodoDTO>> createUser() {
        List<TodoDTO> todos = todoService.findAllForCurrentUser();
        return ResponseEntity.ok(todos);
    }

}
