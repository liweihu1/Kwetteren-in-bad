package com.kwetter.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Tweet {
    private UUID id;
    private String message;
    private Date dateCreated;
    private List<User> mentions;
    private List<User> heartedBy;
    private List<Trend> trends;

    protected Tweet(){

    }

    public Tweet(UUID id, String message, Date dateCreated, List<User> mentions, List<User> heartedBy, List<Trend> trends){
        this.id = id;
        this.message = message;
        this.dateCreated = dateCreated;
        this.mentions = mentions;
        this.heartedBy = heartedBy;
        this.trends = trends;
    }

    public UUID getId() {
        return id;
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
