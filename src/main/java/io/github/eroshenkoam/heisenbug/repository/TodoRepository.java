package io.github.eroshenkoam.heisenbug.repository;

import io.github.eroshenkoam.heisenbug.domain.Todo;
import io.github.eroshenkoam.heisenbug.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByUser(final User user);

}
