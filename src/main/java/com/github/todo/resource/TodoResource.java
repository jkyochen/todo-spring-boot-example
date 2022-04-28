package com.github.todo.resource;

import com.github.todo.applicaiton.todo.TodoApplicationService;
import com.github.todo.applicaiton.todo.dto.TodoIndexParameter;
import com.github.todo.applicaiton.todo.dto.TodoParameter;
import com.github.todo.domain.todo.Todo;
import com.github.todo.resource.request.AddTodoRequest;
import com.github.todo.resource.request.MarkAsDoneRequest;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
                .path("/${id}")
                .buildAndExpand(optionalTodo.get().getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> markTodoDone(@PathVariable final Long id, @RequestBody final MarkAsDoneRequest request) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        if (!request.isDone()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Todo> optionalTodo = this.service.markTodoDone(TodoIndexParameter.of(id));
        if (optionalTodo.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

}
