package com.github.todo.user;

import com.github.todo.applicaiton.user.UserApplicationService;
import com.github.todo.applicaiton.user.dto.UserRegisterParameter;
import com.github.todo.domain.user.User;
import com.github.todo.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
        User user = service.register(UserRegisterParameter.of("foo", "123"));
        assertThat(user.getUserName()).isEqualTo("foo");
        verify(repository).save(any());
    }
}
