package io.github.eroshenkoam.heisenbug.repository;

import io.github.eroshenkoam.heisenbug.IntegrationTest;
import io.github.eroshenkoam.heisenbug.domain.Todo;
import io.github.eroshenkoam.heisenbug.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.github.eroshenkoam.heisenbug.RandomUtils.randomTodo;
import static io.github.eroshenkoam.heisenbug.RandomUtils.randomUser;
import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
@Transactional
public class TodoRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void shouldSaveTodo() {
        final User user = randomUser();
        userRepository.saveAndFlush(user);

        final Todo todo = randomTodo(user);
        todoRepository.saveAndFlush(todo);

        List<Todo> all = todoRepository.findAll();
        assertThat(all).hasSize(1)
            .extracting(Todo::getTitle)
            .contains(todo.getTitle());
    }

}
