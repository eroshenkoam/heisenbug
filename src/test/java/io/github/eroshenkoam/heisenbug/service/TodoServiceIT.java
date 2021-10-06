package io.github.eroshenkoam.heisenbug.service;

import io.github.eroshenkoam.heisenbug.IntegrationTest;
import io.github.eroshenkoam.heisenbug.domain.Todo;
import io.github.eroshenkoam.heisenbug.domain.User;
import io.github.eroshenkoam.heisenbug.repository.TodoRepository;
import io.github.eroshenkoam.heisenbug.repository.UserRepository;
import io.github.eroshenkoam.heisenbug.service.dto.TodoDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.security.RandomUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static io.github.eroshenkoam.heisenbug.RandomUtils.randomTodo;
import static io.github.eroshenkoam.heisenbug.RandomUtils.randomUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Integration tests for {@link UserService}.
 */
@IntegrationTest
@Transactional
class TodoServiceIT {

  @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;


    @Test
    @Transactional
    void assertThatUserMustExistToResetPassword() {
        final User user = randomUser();
        userRepository.saveAndFlush(user);

        final Todo todo = randomTodo(user);
        todoRepository.saveAndFlush(todo);

        List<TodoDTO> dtoList = todoService.findAllForCurrentUser();
        assertThat(dtoList).hasSize(1);
    }

}
