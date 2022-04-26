package com.github.todo.application;

import com.github.todo.applicaiton.todo.TodoApplicationService;
import com.github.todo.applicaiton.todo.dto.TodoIndexParameter;
import com.github.todo.applicaiton.todo.dto.TodoParameter;
import com.github.todo.domain.todo.Todo;
import com.github.todo.domain.todo.TodoRepository;
import com.github.todo.domain.user.User;
import com.github.todo.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TodoApplicationServiceTest {
    private TodoApplicationService service;
    private TodoRepository repository;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        this.repository = mock(TodoRepository.class);
        this.userRepository = mock(UserRepository.class);
        this.service = new TodoApplicationService(this.repository, this.userRepository);
    }

    @Test
    public void should_add_todo() {
        User user = new User("foo", "123");
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(repository.save(any())).then(returnsFirstArg());
        Optional<Todo> optionalTodo = service.addTodo(TodoParameter.of(1L, "todo"));
        assertThat(optionalTodo.get().getContent()).isEqualTo("todo");
        verify(repository).save(any());
    }

    @Test
    public void should_mark_todo_done() {
        User user = new User("foo", "123");
        Todo todo = new Todo(user, "todo");
        when(repository.findById(any())).thenReturn(Optional.of(todo));
        when(repository.save(any())).then(returnsFirstArg());
        Optional<Todo> optionalTodo = service.markTodoDone(TodoIndexParameter.of(1L));
        assertThat(optionalTodo.get().isDone()).isEqualTo(true);
        verify(repository).save(any());
    }

}
