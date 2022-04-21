package com.github.todo.domain.user;

import org.springframework.data.repository.Repository;

public interface TodoUserRepository extends Repository<TodoUser, Long> {
    TodoUser save(TodoUser user);
}
