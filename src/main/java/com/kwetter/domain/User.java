package com.kwetter.domain;

import javax.persistence.Entity;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String biography;
    private String website;
    private String location;
    private List<User> followers;
    private List<User> following;
    private List<Role> roles;
    private List<Tweet> tweets;

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBiography() {
        return biography;
    }

    public String getWebsite() {
        return website;
    }

    public String getLocation() {
        return location;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
