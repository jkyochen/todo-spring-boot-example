package com.github.todo.applicaiton.user;

import com.github.todo.domain.user.User;
import com.github.todo.applicaiton.user.dto.UserRegisterParameter;
import com.github.todo.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

    private final UserRepository repository;

    @Autowired
    public UserApplicationService(final UserRepository repository) {
        this.repository = repository;
    }

    public User register(final UserRegisterParameter userRegisterParameter) {
        if (userRegisterParameter == null) {
            throw new IllegalArgumentException("Null or empty content is not allowed");
        }

        final User user = new User(userRegisterParameter.getUserName(), userRegisterParameter.getPassword());
        return this.repository.save(user);
    }

}
