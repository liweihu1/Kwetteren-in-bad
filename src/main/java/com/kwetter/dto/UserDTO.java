package com.kwetter.dto;

import com.kwetter.domain.Role;
import com.kwetter.domain.Tweet;
import com.kwetter.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String biography;
    private String website;
    private String location;
    private List<UserDTO> followers;
    private List<UserDTO> following;
    private List<String> roles;
    private List<TweetDTO> tweets;

    public UserDTO(){
        //EMPTY CONSTRUCTOR FOR JSON CALLS
    }

    public UserDTO(User user) {
        this.id = user.getId().toString();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.biography = user.getBiography();
        this.website = user.getWebsite();
        this.location = user.getLocation();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.roles = new ArrayList<>();
        this.tweets = new ArrayList<>();
        for(User u : user.getFollowers()){
            this.followers.add(new UserDTO(u));
        }

        for(User u : user.getFollowing()){
            this.following.add(new UserDTO(u));
        }

        for(Tweet t : user.getTweets()){
            this.tweets.add(new TweetDTO(t));
        }

        for(Role r : user.getRoles()){
            this.roles.add(r.toString());
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserDTO> followers) {
        this.followers = followers;
    }

    public List<UserDTO> getFollowing() {
        return following;
    }

    public void setFollowing(List<UserDTO> following) {
        this.following = following;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<TweetDTO> getTweets() {
        return tweets;
    }

    public void setTweets(List<TweetDTO> tweets) {
        this.tweets = tweets;
    }
}
