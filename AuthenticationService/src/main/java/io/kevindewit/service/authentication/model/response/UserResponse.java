package io.kevindewit.service.authentication.model.response;

import io.swagger.annotations.ApiModelProperty;

public class UserResponse {
    private String username;
    @ApiModelProperty(position = 1)
    private String email;

    public UserResponse(String username, String email) {
        this.username = username;
        this.email = email;
    }

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
}
