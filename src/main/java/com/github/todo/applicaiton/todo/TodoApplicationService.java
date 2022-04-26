package com.github.todo.applicaiton.todo;

import com.github.todo.applicaiton.todo.dto.TodoIndexParameter;
import com.github.todo.applicaiton.todo.dto.TodoParameter;
import com.github.todo.domain.todo.Todo;
import com.github.todo.domain.todo.TodoRepository;
import com.github.todo.domain.user.User;
import com.github.todo.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TodoApplicationService {

    private final TodoRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public TodoApplicationService(final TodoRepository repository, final UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Optional<Todo> addTodo(final TodoParameter todoParameter) {
        if (todoParameter == null) {
            throw new IllegalArgumentException("Null or empty content is not allowed");
        }

        Long userId = todoParameter.getUserId();
        Optional<User> optionalUser = this.userRepository.findById(userId);

        return optionalUser.flatMap(user -> doAddTodo(todoParameter, user));
    }

    private Optional<Todo> doAddTodo(TodoParameter todoParameter, User user) {
        final Todo todo = new Todo(user, todoParameter.getContent());
        return Optional.of(this.repository.save(todo));
    }

    public Optional<Todo> markTodoDone(final TodoIndexParameter todoIndexParameter) {
        Optional<Todo> optionalTodo = this.repository.findById(todoIndexParameter.getIndex());
        return optionalTodo.flatMap(this::doMarkAsDone);
    }

    private Optional<Todo> doMarkAsDone(Todo todoItem) {
        todoItem.markDone();
        return Optional.of(this.repository.save(todoItem));
    }

}
