package io.github.eroshenkoam.heisenbug.service;

import io.github.eroshenkoam.heisenbug.IntegrationTest;
import io.github.eroshenkoam.heisenbug.domain.Todo;
import io.github.eroshenkoam.heisenbug.domain.User;
import io.github.eroshenkoam.heisenbug.repository.TodoRepository;
import io.github.eroshenkoam.heisenbug.repository.UserRepository;
import io.github.eroshenkoam.heisenbug.service.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static io.github.eroshenkoam.heisenbug.RandomUtils.randomTodo;
import static io.github.eroshenkoam.heisenbug.RandomUtils.randomUser;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link UserService}.
 */
@IntegrationTest
@Transactional
class TodoServiceIT {

    private static final String USERNAME = "heisenbug";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;


    @Test
    @Transactional
    @WithMockUser(username = USERNAME)
    void assertThatUserMustExistToResetPassword() {
        final User firstUser = randomUser((u) -> u.setLogin(USERNAME));
        userRepository.saveAndFlush(firstUser);
        final User secondUser = randomUser();
        userRepository.saveAndFlush(secondUser);

        final Todo firstTodo = randomTodo(firstUser);
        todoRepository.saveAndFlush(firstTodo);
        final Todo secondTodo = randomTodo(firstUser);
        todoRepository.saveAndFlush(secondTodo);

        final Todo unusedTodo = randomTodo(secondUser);
        todoRepository.saveAndFlush(unusedTodo);

        List<TodoDTO> dtoList = todoService.findAllForCurrentUser();
        assertThat(dtoList).hasSize(2)
            .extracting(TodoDTO::getTitle)
            .contains(firstTodo.getTitle(), secondTodo.getTitle());
    }

}
