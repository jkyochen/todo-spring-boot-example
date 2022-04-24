package com.github.todo.domain.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.todo.domain.BaseEntity;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "user_name")
    private String username;

    @Column
    private String password;

    public User(final String userName, final String password) {
        this.username = userName;
        this.password = this.encodePassword(password);
    }

    public void assignUserName(final String username) {
        this.username = username;
    }

    public void changePassword(final String password) {
        this.password = this.encodePassword(password);
    }

    public boolean validatePassword(final String password) {
        return new BCryptPasswordEncoder().matches(password, this.password);
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
