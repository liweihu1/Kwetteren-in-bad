package com.kwetter.domain;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name="user.getAllUsers", query = "SELECT u FROM User u"),
        @NamedQuery(name="user.checkUsernameAvailability", query = "Select u FROM User u WHERE u.username = :username")
})
public class User {
    @Id
    @Column( columnDefinition = "BINARY(16)", length = 16 )
    private UUID id;
    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    @Column(length = 160)
    private String biography;
    private String website;
    private String location;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> followers;

    @ManyToMany(cascade = CascadeType.ALL)
    @OrderBy(value = "username DESC")
    private List<User> following;

    @ElementCollection
    private List<Role> roles;

    @OneToMany
    private List<Tweet> tweets;

    @ManyToMany(cascade = CascadeType.ALL)
    @OrderBy(value = "dateUpdated DESC")
    private List<Tweet> heartedTweets;

    @ManyToMany(cascade = CascadeType.ALL)
    @OrderBy(value = "dateUpdated DESC")
    private List<Tweet> mentionedTweets;

    protected User() {

    }

    public User(UUID id, String username, String firstName, String lastName, String biography, String website, String location, List<User> followers, List<User> following, List<Role> roles, List<Tweet> tweets, List<Tweet> heartedTweets, List<Tweet> mentionedTweets) {
        this.id = id;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
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
