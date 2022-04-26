package com.github.todo.application;

import com.github.todo.applicaiton.user.UserApplicationService;
import com.github.todo.applicaiton.user.dto.UserLoginParameter;
import com.github.todo.domain.user.User;
import com.github.todo.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserApplicationServiceTest {
    private UserApplicationService service;
    private UserRepository repository;

    @BeforeEach
    public void setUp() {
        this.repository = mock(UserRepository.class);
        this.service = new UserApplicationService(this.repository);
    }

    @Test
    public void should_register_user() {
        when(repository.save(any())).then(returnsFirstArg());
        User user = service.register(UserLoginParameter.of("foo", "123"));
        assertThat(user.getUsername()).isEqualTo("foo");
        verify(repository).save(any());
    }

    @Test
    public void should_throw_exception_for_register_dup_username_user() {
        when(repository.existsByUsername(any())).thenReturn(true);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> service.register(UserLoginParameter.of("foo", "123")));
    }

    @Test
    public void should_login_user() {
        final User foo = new User("foo", "123");
        when(repository.findByUsername(any())).thenReturn(Optional.of(foo));
        Optional<User> user = service.login(UserLoginParameter.of("foo", "123"));
        assertThat(user.isPresent()).isEqualTo(true);
        assertThat(user.get().getUsername()).isEqualTo("foo");
    }

    @Test
    public void should_throw_exception_for_login_error_password() {
        final User foo = new User("foo", "123");
        when(repository.findByUsername(any())).thenReturn(Optional.of(foo));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> service.login(UserLoginParameter.of("foo", "1234")));
    }
}
