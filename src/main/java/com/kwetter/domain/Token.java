package com.kwetter.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.UUID;

@Entity
@NamedQuery(name = "token.getTokenByUserId", query = "SELECT t FROM Token t WHERE t.user.id = :userId")
public class Token {
    @Id
    private UUID id;

    @OneToOne
    private User user;

    private Date expireDate;

    protected Token(){

    }

    public Token(User user){
        this.user = user;
        this.expireDate = new Date(new Date().getTime() + 50000L);
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public User getUser() {
        return user;
    }

    public UUID getId() {
        return id;
    }
}
