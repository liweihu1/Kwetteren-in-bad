package com.kwetter.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NamedQueries({
    @NamedQuery(name = "token.getTokenByUserId", query = "SELECT t FROM Token t WHERE t.user.id = :userId"),
    @NamedQuery(name = "token.validateLogin", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
})
public class Token {
    @Id
    private UUID id;

    @OneToOne
    private User user;

    private Date expireDate;

    protected Token(){

    }

    public Token(User user){
        this.id = UUID.randomUUID();
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

    @Override
    public String toString(){
        return id + " " + user.getUsername() + " " + expireDate.toString();
    }
}
