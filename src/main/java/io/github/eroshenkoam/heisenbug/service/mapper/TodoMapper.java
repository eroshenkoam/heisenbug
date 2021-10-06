package io.github.eroshenkoam.heisenbug.service.mapper;

import io.github.eroshenkoam.heisenbug.domain.Authority;
import io.github.eroshenkoam.heisenbug.domain.Todo;
import io.github.eroshenkoam.heisenbug.domain.User;
import io.github.eroshenkoam.heisenbug.service.dto.AdminUserDTO;
import io.github.eroshenkoam.heisenbug.service.dto.TodoDTO;
import io.github.eroshenkoam.heisenbug.service.dto.UserDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDTO}.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class TodoMapper {

    public TodoDTO todoToDto(final Todo todo) {
        TodoDTO dto = new TodoDTO();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        return dto;
    }

}
