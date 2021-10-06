package io.github.eroshenkoam.heisenbug.web.rest;

import io.github.eroshenkoam.heisenbug.IntegrationTest;
import io.github.eroshenkoam.heisenbug.service.TodoService;
import io.github.eroshenkoam.heisenbug.service.UserService;
import io.github.eroshenkoam.heisenbug.service.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.util.Arrays;
import java.util.List;

import static io.github.eroshenkoam.heisenbug.RandomUtils.randomTodo;
import static io.github.eroshenkoam.heisenbug.RandomUtils.randomUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for {@link UserService}.
 */
@AutoConfigureMockMvc
@IntegrationTest
@Transactional
class TodoResourceIT {

    @MockBean
    private TodoService todoService;

    @Autowired
    private MockMvc restUserMockMvc;

    @Test
    @WithMockUser
    void shouldGetAllTodos() throws Exception {
        final TodoDTO firstTodo = new TodoDTO()
            .setTitle(RandomStringUtils.randomAlphabetic(10));
        final TodoDTO secondTodo = new TodoDTO()
            .setTitle(RandomStringUtils.randomAlphabetic(10));
        when(todoService.findAllForCurrentUser())
            .thenReturn(Arrays.asList(firstTodo, secondTodo));

        restUserMockMvc
            .perform(get("/api/todo").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].title").value(hasItems(
                firstTodo.getTitle(),
                secondTodo.getTitle()
            )));
    }

}
