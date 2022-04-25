package com.github.todo.applicaiton.todo;

import com.github.todo.applicaiton.todo.dto.TodoIndexParameter;
import com.github.todo.applicaiton.todo.dto.TodoParameter;
import com.github.todo.domain.todo.Todo;
import com.github.todo.domain.todo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TodoApplicationService {

    private final TodoRepository repository;

    @Autowired
    public TodoApplicationService(final TodoRepository repository) {
        this.repository = repository;
    }

    public Todo addTodo(final TodoParameter todoParameter) {
        if (todoParameter == null) {
            throw new IllegalArgumentException("Null or empty content is not allowed");
        }

        final Todo todo = new Todo(todoParameter.getContent());
        return this.repository.save(todo);
    }

    public Optional<Todo> markTodoDone(final TodoIndexParameter todoIndexParameter) {
        Optional<Todo> optionalTodo = this.repository.findById(todoIndexParameter.getIndex());
        return optionalTodo.flatMap(todoItem -> {
            todoItem.markDone();
            return Optional.of(this.repository.save(todoItem));
        });
    }

}
