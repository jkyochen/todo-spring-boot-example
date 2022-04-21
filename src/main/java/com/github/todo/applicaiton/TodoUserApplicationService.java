package com.github.todo.applicaiton;

import com.github.todo.domain.user.TodoUser;
import com.github.todo.applicaiton.dto.UserRegisterParameter;
import com.github.todo.domain.user.TodoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoUserApplicationService {

    private final TodoUserRepository repository;

    @Autowired
    public TodoUserApplicationService(final TodoUserRepository repository) {
        this.repository = repository;
    }

    public TodoUser register(final UserRegisterParameter userRegisterParameter) {
        if (userRegisterParameter == null) {
            throw new IllegalArgumentException("Null or empty content is not allowed");
        }

        final TodoUser user = new TodoUser(userRegisterParameter.getUserName(), userRegisterParameter.getPassword());
        return this.repository.save(user);
    }

}
