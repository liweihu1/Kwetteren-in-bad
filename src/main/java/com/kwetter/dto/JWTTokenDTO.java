package com.kwetter.dto;

import java.util.UUID;

public class JWTTokenDTO {
    private String username;
    private String token;
    private UUID id;

    public JWTTokenDTO(String username, String token, UUID id) {
        this.username = username;
        this.token = token;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
