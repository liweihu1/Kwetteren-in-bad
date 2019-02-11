package com.kwetter.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
public class Trend {
    @Id
    private UUID id;

    private String name;

    @OneToMany
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
