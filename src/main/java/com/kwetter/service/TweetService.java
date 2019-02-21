package com.kwetter.service;

import com.kwetter.dao.interfaces.TweetDAO;
import com.kwetter.domain.Tweet;
import com.kwetter.dto.TweetDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Stateless
public class TweetService {
    @Inject
    private TweetDAO tweetDAO;

    public List<Tweet> getTweetsForUserId(UUID userId){
        return tweetDAO.getLatestTweetsForUserId(userId);
    }

    public Tweet getTweetById(UUID id){
        return tweetDAO.findById(id);
    }

    public boolean createTweet(Tweet tweet){
        return tweetDAO.add(tweet) != null;
    }

    public List<Tweet> getAllTweets(){
        return tweetDAO.getAllTweets();
    }

    public List<Tweet> getTweetsBySearchString(String search){
        return tweetDAO.getTweetThatContainsSearch(search);
    }
}
