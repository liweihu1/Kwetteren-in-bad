package com.kwetter.domain;

import java.util.List;

public class Trend {
    private String name;
    private List<Tweet> tweets;

    public String getName() {
        return name;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
