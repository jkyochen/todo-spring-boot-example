package com.github.todo.domain.todo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.todo.domain.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class Todo extends BaseEntity {

    @Column
    private String content;

    @Column
    private boolean done;

    public Todo(final String content) {
        this.content = content;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

}
