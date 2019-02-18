package com.kwetter.dto;

import com.kwetter.domain.User;

public class UserDTO {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String biography;
    private String website;
    private String location;

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
}
