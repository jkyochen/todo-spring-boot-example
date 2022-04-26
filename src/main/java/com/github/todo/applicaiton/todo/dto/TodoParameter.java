package com.github.todo.applicaiton.todo.dto;

import com.google.common.base.Strings;
import lombok.Getter;

public final class TodoParameter {
    @Getter
    private final Long userId;
    @Getter
    private final String content;

    private TodoParameter(final Long userId, final String content) {
        this.userId = userId;
        this.content = content;
    }

    public static TodoParameter of(final Long userId, final String content) {
        if (userId <= 0) {
            throw new IllegalArgumentException("Error userId");
        }
        if (Strings.isNullOrEmpty(content)) {
            throw new IllegalArgumentException("Empty content is not allowed");
        }

        return new TodoParameter(userId, content);
    }
}
