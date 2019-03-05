package io.kevindewit.service.authentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @Column(name = "ROLE_ID")
    private UUID uuid;
    @Column(nullable = false, unique = true)
    private String role;

    public Role() {
    }

    public Role(String role) {
        this.uuid = UUID.randomUUID();
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
