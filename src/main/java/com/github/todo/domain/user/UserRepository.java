package com.github.todo.domain.user;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {
    User save(User user);

    boolean existsByUsername(String username);
}
