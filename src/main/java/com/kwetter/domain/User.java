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

}
