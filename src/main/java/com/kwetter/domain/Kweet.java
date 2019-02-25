package com.kwetter.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "kweet.getLatestForUser", query = "SELECT t FROM Kweet t WHERE t.author.id = :userId ORDER BY t.dateCreated DESC"),
        @NamedQuery(name = "kweet.getAllKweets", query = "SELECT t FROM Kweet t"),
        @NamedQuery(name = "kweet.getKweetWithMessage", query = "SELECT t FROM Kweet t WHERE t.message LIKE :search"),
        @NamedQuery(name = "kweet.getKweetForUserAndFollowing", query = "SELECT  t FROM Kweet t WHERE t.author.id = :userId OR t.author.id IN (SELECT f1.id FROM User u1 INNER JOIN u1.following f1 WHERE u1.id = :userId) ORDER BY t.dateCreated DESC")
})
public class Kweet {
    @Id
    @Column( columnDefinition = "BINARY(16)", length = 16 )
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @Column(length = 140)
    private String message;
    @CreationTimestamp
    private Date dateCreated;
    @UpdateTimestamp
    private Date dateUpdated;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "mentionedKweets")
    @OrderBy(value = "username DESC")
    private List<User> mentions;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "heartedKweets")
    @OrderBy(value = "username DESC")
    private List<User> heartedBy;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "kweets")
    @OrderBy(value = "name DESC")
    private List<Trend> trends;
    private int reportedAmount;

    protected Kweet(){

    }

    public Kweet(UUID id, User author, String message, Date dateCreated, Date dateUpdated, List<User> mentions, List<User> heartedBy, List<Trend> trends, int reportedAmount){
        this.id = id;
        this.author = author;
        this.message = message;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.mentions = mentions;
        this.heartedBy = heartedBy;
        this.trends = trends;
        this.reportedAmount = reportedAmount;
    }

    public UUID getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public List<User> getHeartedBy() {
        return heartedBy;
    }

    public List<Trend> getTrends() {
        return trends;
    }
}
