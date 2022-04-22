package com.github.todo.applicaiton.user.dto;

import com.google.common.base.Strings;
import lombok.Getter;

public class UserRegisterParameter {
    @Getter
    private final String userName;

    @Getter
    private final String password;

    private UserRegisterParameter(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }

    public static UserRegisterParameter of(final String userName, final String password) {
        if (Strings.isNullOrEmpty(userName)) {
            throw new IllegalArgumentException("Empty userName is not allowed");
        }
        if (Strings.isNullOrEmpty(password)) {
            throw new IllegalArgumentException("Empty password is not allowed");
        }
        return new UserRegisterParameter(userName, password);
    }

}
