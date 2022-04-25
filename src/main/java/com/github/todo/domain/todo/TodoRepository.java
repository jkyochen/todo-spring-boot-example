package com.github.todo.domain.todo;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface TodoRepository extends Repository<Todo, Long> {
    Todo save(Todo todo);

    Optional<Todo> findById(Long id);

    Iterable<Todo> findAll();
}
