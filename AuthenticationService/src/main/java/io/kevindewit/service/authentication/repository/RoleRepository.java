package io.kevindewit.service.authentication.repository;

import io.kevindewit.service.authentication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByRole(String role);
}
