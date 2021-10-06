package io.github.eroshenkoam.heisenbug.service;

import io.github.eroshenkoam.heisenbug.config.Constants;
import io.github.eroshenkoam.heisenbug.domain.Authority;
import io.github.eroshenkoam.heisenbug.domain.Todo;
import io.github.eroshenkoam.heisenbug.domain.User;
import io.github.eroshenkoam.heisenbug.repository.AuthorityRepository;
import io.github.eroshenkoam.heisenbug.repository.TodoRepository;
import io.github.eroshenkoam.heisenbug.repository.UserRepository;
import io.github.eroshenkoam.heisenbug.security.AuthoritiesConstants;
import io.github.eroshenkoam.heisenbug.security.SecurityUtils;
import io.github.eroshenkoam.heisenbug.service.dto.AdminUserDTO;
import io.github.eroshenkoam.heisenbug.service.dto.TodoDTO;
import io.github.eroshenkoam.heisenbug.service.dto.UserDTO;
import io.github.eroshenkoam.heisenbug.service.mapper.TodoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.security.RandomUtil;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class TodoService {

    private final UserRepository userRepository;

    private final TodoMapper todoMapper;
    private final TodoRepository todoRepository;

    public TodoService(final UserRepository userRepository,
                       final TodoMapper todoMapper,
                       final TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoMapper = todoMapper;
        this.todoRepository = todoRepository;
    }

    public List<TodoDTO> findAllForCurrentUser() {
        User user = getUserWithAuthorities()
            .orElseThrow(() -> new NullPointerException("Can't find authorized user"));

        List<Todo> todos = todoRepository.findAllByUser(user);
        return todos.stream()
            .map(todoMapper::todoToDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findOneWithAuthoritiesByLogin);
    }

}
