package com.kwetter.dto;

import com.kwetter.domain.Token;
import java.util.Date;
import java.util.UUID;

public class TokenDTO {
    private UUID id;
    private UserDTO user;

    private Date expireDate;

    public TokenDTO(Token token){
        this.id = token.getId();
        this.user = new UserDTO(token.getUser());
        this.expireDate = token.getExpireDate();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
