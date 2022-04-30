package com.github.todo.resource;

import com.github.todo.DBRollbackBaseTest;
import com.github.todo.domain.user.User;
import com.github.todo.domain.user.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserResourceTest extends DBRollbackBaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;

    @AfterAll
    public void tearDown() {
        this.repository.deleteAll();
    }

    @Test
    public void should_register_user() throws Exception {
        String userParam = "{" + "\"username\": \"foo\"" + ", \"password\": \"123\"" + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userParam))
                .andReturn();

        assertThat(repository.existsByUsername("foo")).isEqualTo(true);
    }

    @Test
    public void should_login_user() throws Exception {
        String userParam = "{" + "\"username\": \"foo\"" + ", \"password\": \"123\"" + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userParam))
                .andExpect(status().isCreated());
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userParam))
                .andExpect(status().isOk());
    }

}
