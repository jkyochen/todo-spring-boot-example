package com.github.todo.resource.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class MarkAsDoneRequest {
    @Getter
    private final boolean done;

    @JsonCreator
    public MarkAsDoneRequest(@JsonProperty("done") final boolean done) {
        this.done = done;
    }

}
