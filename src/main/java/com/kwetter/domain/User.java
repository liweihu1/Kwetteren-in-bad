package com.kwetter.domain;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String biography;
    private String website;
    private String location;

    @OneToMany
    private List<User> followers;

    @OneToMany
    private List<User> following;

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @OneToMany
    private List<Tweet> tweets;

    @OneToMany
    private List<Tweet> heartedTweets;

    @OneToMany
    private List<Tweet> mentionedTweets;

    protected User() {

    }

    public User(UUID id,  String firstName, String lastName, String biography, String website, String location, List<User> followers, List<User> following, List<Role> roles, List<Tweet> tweets, List<Tweet> heartedTweets, List<Tweet> mentionedTweets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.website = website;
        this.location = location;
        this.followers = followers;
        this.following = following;
        this.roles = roles;
        this.tweets = tweets;
        this.heartedTweets = heartedTweets;
        this.mentionedTweets = mentionedTweets;
    }

    public UUID getId() {
        return id;
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

    public List<Tweet> getHeartedTweets() {
        return heartedTweets;
    }

    public List<Tweet> getMentionedTweets() {
        return mentionedTweets;
    }
}
