package com.kwetter.dto;

import com.kwetter.domain.Trend;
import com.kwetter.domain.Tweet;
import com.kwetter.domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TweetDTO {
    private String id;
    private String authorId;
    private String message;
    private Date dateCreated;
    private Date dateUpdated;
    private List<UserDTO> mentions;
    private List<UserDTO> heartedBy;
    private List<TrendDTO> trends;

    public TweetDTO(){

    }

    public TweetDTO(Tweet tweet){
        this.id = tweet.getId().toString();
        this.authorId = tweet.getAuthor().getId().toString();
        this.message = tweet.getMessage();
        this.dateCreated = tweet.getDateCreated();
        this.dateUpdated = tweet.getDateUpdated();
        this.mentions = new ArrayList<>();
        this.heartedBy = new ArrayList<>();
        this.trends = new ArrayList<>();
        for (User u : tweet.getMentions()){
            this.mentions.add(new UserDTO(u));
        }

        for(User u: tweet.getHeartedBy()){
            this.heartedBy.add(new UserDTO(u));
        }

        for (Trend t: tweet.getTrends()){
            this.trends.add(new TrendDTO(t));
        }
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

    public List<UserDTO> getMentions() {
        return mentions;
    }

    public void setMentions(List<UserDTO> mentions) {
        this.mentions = mentions;
    }

    public List<UserDTO> getHeartedBy() {
        return heartedBy;
    }

    public void setHeartedBy(List<UserDTO> heartedBy) {
        this.heartedBy = heartedBy;
    }

    public List<TrendDTO> getTrends() {
        return trends;
    }

    public void setTrends(List<TrendDTO> trends) {
        this.trends = trends;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
