package com.github.todo.applicaiton.user;

import com.github.todo.domain.user.User;
import com.github.todo.applicaiton.user.dto.UserLoginParameter;
import com.github.todo.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationService {

    private final UserRepository repository;

    @Autowired
    public UserApplicationService(final UserRepository repository) {
        this.repository = repository;
    }

    public User register(final UserLoginParameter userRegisterParameter) {
        if (userRegisterParameter == null) {
            throw new IllegalArgumentException("Null or empty content is not allowed");
        }
        if (this.repository.existsByUsername(userRegisterParameter.getUsername())) {
            throw new IllegalArgumentException("Existed username");
        }

        final User user = new User(userRegisterParameter.getUsername(), userRegisterParameter.getPassword());
        return this.repository.save(user);
    }

    public Optional<User> login(final UserLoginParameter userLoginParameter) {
        if (userLoginParameter == null) {
            throw new IllegalArgumentException("Null or empty content is not allowed");
        }

        Optional<User> optionalUser = this.repository.findByUsername(userLoginParameter.getUsername());

        return optionalUser.flatMap(user -> {
            if (!user.validatePassword(userLoginParameter.getPassword())) {
                throw new IllegalArgumentException("Password is error");
            }
            return Optional.of(user);
        });
    }

}
