package com.github.todo.resource;

import com.github.todo.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;

    @Test
    public void should_register_user() throws Exception {
        String userParam = "{" + "\"username\": \"foo\"" + ", \"password\": \"123\"" + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userParam))
                .andExpect(status().isCreated());
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
