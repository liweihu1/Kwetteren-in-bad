package com.kwetter.domain;

import java.util.List;

public class Trend {
    private String name;
    private List<Tweet> tweets;

    protected  Trend() {

    }

    public Trend(String name, List<Tweet> tweets) {
        this.name = name;
        this.tweets = tweets;
    }

    public String getName() {
        return name;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
