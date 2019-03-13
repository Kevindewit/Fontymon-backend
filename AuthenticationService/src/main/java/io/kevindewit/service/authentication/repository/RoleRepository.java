package io.kevindewit.service.authentication.repository;

import io.kevindewit.service.authentication.model.ROLE_NAME;
import io.kevindewit.service.authentication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ROLE_NAME roleName);
}
