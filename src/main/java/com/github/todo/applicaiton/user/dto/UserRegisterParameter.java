package com.github.todo.applicaiton.user.dto;

import com.google.common.base.Strings;
import lombok.Getter;

public class UserRegisterParameter {
    @Getter
    private final String username;

    @Getter
    private final String password;

    private UserRegisterParameter(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public static UserRegisterParameter of(final String username, final String password) {
        if (Strings.isNullOrEmpty(username)) {
            throw new IllegalArgumentException("Empty username is not allowed");
        }
        if (Strings.isNullOrEmpty(password)) {
            throw new IllegalArgumentException("Empty password is not allowed");
        }
        return new UserRegisterParameter(username, password);
    }

}
