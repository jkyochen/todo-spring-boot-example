package com.github.todo.domain.todo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.todo.domain.BaseEntity;
import com.github.todo.domain.user.User;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class Todo extends BaseEntity {

    @Column
    private String content;

    @Column
    private boolean done;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(final User user, final String content) {
        this.user = user;
        this.content = content;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

}
