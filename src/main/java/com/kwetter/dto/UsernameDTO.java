package com.kwetter.dto;

import java.util.UUID;

public class UsernameDTO {
    private String username;
    private UUID userId;

    public UsernameDTO(){

    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
