package com.kwetter.service;

import com.kwetter.dao.interfaces.TweetDAO;
import com.kwetter.domain.Tweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

@Stateless
public class TweetService {
    @Inject
    private TweetDAO tweetDAO;

    public Tweet getTweetById(UUID id){
        return tweetDAO.findById(id);
    }

    public boolean createTweet(Tweet tweet){
        return tweetDAO.add(tweet) != null;
    }
}
