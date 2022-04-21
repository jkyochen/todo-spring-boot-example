package com.github.todo.domain.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "todo_users")
public class TodoUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;

    @Column(name = "user_name")
    private String userName;

    @Column
    private String password;

    public TodoUser(final String userName, final String password) {
        this.userName = userName;
        this.password = this.encodePassword(password);
    }

    public void assignUserName(final String userName) {
        this.userName = userName;
    }

    public void changePassword(final String password) {
        this.password = this.encodePassword(password);
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
