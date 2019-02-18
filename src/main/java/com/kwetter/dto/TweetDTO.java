package com.kwetter.dto;

import com.kwetter.domain.Tweet;
import java.util.Date;

public class TweetDTO {
    private String id;
    private String authorId;
    private String message;
    private Date dateCreated;
    private Date dateUpdated;

    public TweetDTO(){

    }

    public TweetDTO(Tweet tweet){
        this.id = tweet.getId().toString();
        this.authorId = tweet.getAuthor().getId().toString();
        this.message = tweet.getMessage();
        this.dateCreated = tweet.getDateCreated();
        this.dateUpdated = tweet.getDateUpdated();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
