package com.github.todo.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.todo.domain.todo.Todo;
import com.github.todo.domain.todo.TodoRepository;
import com.github.todo.domain.user.User;
import com.github.todo.domain.user.UserRepository;
import com.github.todo.resource.request.MarkAsDoneRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TodoResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    private User user;

    @BeforeAll
    public void setUp() {
        User u = new User("foo", "123");
        user = userRepository.save(u);
    }

    @Test
    public void should_add_todo() throws Exception {

        final String todo = "{ " +
                "\"userId\":" + user.getId() +
                ", \"content\": \"foo\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todo))
                .andExpect(status().isCreated());
        assertThat(todoRepository.findAll()).anyMatch(item -> item.getContent().equals("foo"));
    }

    @Test
    public void should_mark_todo_done() throws Exception {

        final Todo newTodo = new Todo(user, "do homework");
        final Todo todo = todoRepository.save(newTodo);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String request = objectWriter.writeValueAsString(new MarkAsDoneRequest(true));

        mockMvc.perform(MockMvcRequestBuilders.put("/todo/" + todo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk());

        assertThat(todoRepository.findById(todo.getId())).matches(item -> item.get().isDone());
    }

}
