package com.kwetter.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Tweet {
    @Id
    @Column( columnDefinition = "BINARY(16)", length = 16 )
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @Column(length = 150)
    private String message;
    @CreationTimestamp
    private Date dateCreated;
    @UpdateTimestamp
    private Date dateUpdated;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> mentions;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> heartedBy;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Trend> trends;
    private int reportedAmount;

    protected Tweet(){

    }

    public Tweet(UUID id, User author, String message, Date dateCreated, Date dateUpdated, List<User> mentions, List<User> heartedBy, List<Trend> trends){
        this.id = id;
        this.author = author;
        this.message = message;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.mentions = mentions;
        this.heartedBy = heartedBy;
        this.trends = trends;
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
