package com.kwetter.domain;

import java.util.List;
import java.util.UUID;

public class Trend {
    private UUID id;
    private String name;
    private List<Tweet> tweets;

    protected  Trend() {

    }

    public Trend(UUID id, String name, List<Tweet> tweets) {
        this.id = id;
        this.name = name;
        this.tweets = tweets;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
