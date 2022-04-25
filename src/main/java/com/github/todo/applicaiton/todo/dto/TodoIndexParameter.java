package com.github.todo.applicaiton.todo.dto;

import lombok.Getter;

public class TodoIndexParameter {
    @Getter
    private final Long index;

    public TodoIndexParameter(final Long index) {
        this.index = index;
    }

    public static TodoIndexParameter of(final Long index) {
        if (index <= 0) {
            throw new IllegalArgumentException("Todo index should be greater than 0");
        }

        return new TodoIndexParameter(index);
    }
}
