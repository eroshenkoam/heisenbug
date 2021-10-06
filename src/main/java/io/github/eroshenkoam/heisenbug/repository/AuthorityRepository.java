package io.github.eroshenkoam.heisenbug.repository;

import io.github.eroshenkoam.heisenbug.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
