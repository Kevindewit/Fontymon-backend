package io.kevindewit.service.user.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @Column(name = "USER_ID")
    private UUID uuid;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    @JsonIgnore
    private boolean enabled;

    public User() {
    }

    //region getters / setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    //endregion
}
