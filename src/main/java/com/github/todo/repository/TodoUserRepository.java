package com.github.todo.repository;

import com.github.todo.domain.TodoUser;
import org.springframework.data.repository.Repository;

public interface TodoUserRepository extends Repository<TodoUser, Long> {
    TodoUser save(TodoUser user);
}
