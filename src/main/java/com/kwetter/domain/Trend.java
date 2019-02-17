package com.kwetter.domain;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Trend {
    @Id
    @Column( columnDefinition = "BINARY(16)", length = 16 )
    private UUID id;

    @Column(unique = true)
    private String name;

    @ManyToMany
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
