package com.github.todo.resource.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class AddTodoRequest {
    @Getter
    private final Long userId;
    @Getter
    private final String content;

    @JsonCreator
    public AddTodoRequest(@JsonProperty("userId") final Long userId, @JsonProperty("content") final String content) {
        this.userId = userId;
        this.content = content;
    }
}
