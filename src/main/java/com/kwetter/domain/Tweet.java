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

    public Tweet(){

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
