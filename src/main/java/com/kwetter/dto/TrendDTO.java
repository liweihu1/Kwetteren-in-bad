package com.kwetter.dto;

import com.kwetter.domain.Trend;
import com.kwetter.domain.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TrendDTO {
    private String id;
    private String name;
    private List<TweetDTO> tweets;

    public TrendDTO(){

    }

    public TrendDTO(Trend trend){
        this.id = trend.getId().toString();
        this.name = trend.getName();
        this.tweets = new ArrayList<>();
        for (Tweet t : trend.getTweets()){
            this.tweets.add(new TweetDTO(t));
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TweetDTO> getTweets() {
        return tweets;
    }

    public void setTweets(List<TweetDTO> tweets) {
        this.tweets = tweets;
    }
}
