package com.kwetter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name="user.getAllUsers", query = "SELECT u FROM User u"),
        @NamedQuery(name="user.findByUsername", query = "Select u FROM User u WHERE u.username = :username"),
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "User_Following",
            joinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "following_id", referencedColumnName = "id")
    )
    @JsonIgnore
    private Set<User> followers;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "followers", fetch = FetchType.EAGER)
    @OrderBy(value = "username DESC")
    @JsonIgnore
    private Set<User> following;

    @ElementCollection(fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Role> roles;

    @OneToMany
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Kweet> Kweets;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "User_Hearted",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "kweet_id", referencedColumnName = "id")
    )
    @OrderBy(value = "dateUpdated DESC")
    @JsonIgnore
    private List<Kweet> heartedKweets;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "User_Mentions",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "kweet_id", referencedColumnName = "id")
    )
    @OrderBy(value = "dateUpdated DESC")
    @JsonIgnore
    private List<Kweet> mentionedKweets;

    protected User() {

    }

    public User(UUID id, String username, String firstName, String lastName, String biography, String website, String location, Set<User> followers, Set<User> following, List<Role> roles, List<Kweet> Kweets, List<Kweet> heartedKweets, List<Kweet> mentionedKweets) {
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
        this.Kweets = Kweets;
        this.heartedKweets = heartedKweets;
        this.mentionedKweets = mentionedKweets;
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

    public Set<User> getFollowers() {
        return followers;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<Kweet> getKweets() {
        return Kweets;
    }

    public List<Kweet> getHeartedKweets() {
        return heartedKweets;
    }

    public List<Kweet> getMentionedKweets() {
        return mentionedKweets;
    }
}
