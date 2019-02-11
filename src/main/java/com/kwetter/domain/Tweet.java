package com.kwetter.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Tweet {
    @Id
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    private String message;
    private Date dateCreated;

    @OneToMany
    private List<User> mentions;
    @OneToMany
    private List<User> heartedBy;
    @OneToMany
    private List<Trend> trends;

    protected Tweet(){

    }

    public Tweet(UUID id, User author, String message, Date dateCreated, List<User> mentions, List<User> heartedBy, List<Trend> trends){
        this.id = id;
        this.author = author;
        this.message = message;
        this.dateCreated = dateCreated;
        this.mentions = mentions;
        this.heartedBy = heartedBy;
        this.trends = trends;
    }

    public UUID getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public List<User> getHeartedBy() {
        return heartedBy;
    }

    public List<Trend> getTrends() {
        return trends;
    }
}
