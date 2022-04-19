package com.github.todo.service;

import com.github.todo.domain.TodoUser;
import com.github.todo.parameter.UserRegisterParameter;
import com.github.todo.repository.TodoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoUserService {

    private final TodoUserRepository repository;

    @Autowired
    public TodoUserService(final TodoUserRepository repository) {
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
