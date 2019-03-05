package io.kevindewit.service.authentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "AUTHENTICATION_PARTICIPANT")
public class Participant {

    @Id
    @Column(name = "PARTICIPANT_ID")
    private UUID uuid;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean enabled = false;
    @Column(unique = true)
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PARTICIPANT_ROLE", joinColumns = @JoinColumn(name = "PARTICIPANT_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

    public Participant() {
    }

    public Participant(String username, String password, String email, Set<Role> roles) {
        this.uuid = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.enabled = true;
        this.email = email;
        this.roles = roles;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID id) {
        this.uuid = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
