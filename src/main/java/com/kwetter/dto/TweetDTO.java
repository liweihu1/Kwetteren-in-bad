package com.kwetter.dto;

import com.kwetter.domain.Trend;
import com.kwetter.domain.Tweet;
import com.kwetter.domain.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TweetDTO {
    private UUID id;
    private User author;
    private String message;
    private Date dateCreated;
    private Date dateUpdated;
    private List<User> mentions;
    private List<User> heartedBy;
    private List<Trend> trends;

    public TweetDTO(){

    }

    public TweetDTO(Tweet tweet){
        this.id = tweet.getId();
        this.author = tweet.getAuthor();
        this.message = tweet.getMessage();
        this.dateCreated = tweet.getDateCreated();
        this.dateUpdated = tweet.getDateUpdated();
        this.mentions = tweet.getMentions();
        this.heartedBy = tweet.getHeartedBy();
        this.trends = tweet.getTrends();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public List<User> getHeartedBy() {
        return heartedBy;
    }

    public void setHeartedBy(List<User> heartedBy) {
        this.heartedBy = heartedBy;
    }

    public List<Trend> getTrends() {
        return trends;
    }

    public void setTrends(List<Trend> trends) {
        this.trends = trends;
    }
}
