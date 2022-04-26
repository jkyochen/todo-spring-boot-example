package com.github.todo.resource;

import com.github.todo.applicaiton.todo.TodoApplicationService;
import com.github.todo.applicaiton.todo.dto.TodoParameter;
import com.github.todo.domain.todo.Todo;
import com.github.todo.resource.request.AddTodoRequest;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodoResource {
    private final TodoApplicationService service;

    @Autowired
    public TodoResource(TodoApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody final AddTodoRequest request) {
        if (request.getUserId() <= 0 || Strings.isNullOrEmpty((request.getContent()))) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Todo> optionalTodo = this.service.addTodo(TodoParameter.of(request.getUserId(), request.getContent()));

        if (optionalTodo.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
