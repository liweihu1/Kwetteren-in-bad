package com.kwetter.dao.memory;

import com.kwetter.dao.database.MemoryDatabase;
import com.kwetter.dao.interfaces.TweetDAO;
import com.kwetter.domain.Tweet;

import javax.ejb.EJB;
import javax.enterprise.inject.Alternative;
import java.util.List;
import java.util.UUID;

@Alternative
public class TweetDAOMEMImpl implements TweetDAO {

    @EJB
    private MemoryDatabase database;

    @Override
    public Tweet add(Tweet tweet) {
        return null;
    }

    @Override
    public void delete(Tweet tweet) {

    }

    @Override
    public Tweet update(Tweet tweet) {
        return null;
    }

    @Override
    public Tweet findById(UUID id) {
        return null;
    }

    @Override
    public List<Tweet> getAllTweets() {
        return null;
    }

    @Override
    public List<Tweet> getLatestTweetsForUserId(UUID id) {
        return null;
    }

    @Override
    public List<Tweet> getTweetThatContainsSearch(String search) {
        return null;
    }

    @Override
    public List<Tweet> getTweetLikesForUserId(UUID id) {
        return null;
    }

    @Override
    public List<Tweet> getAllTweetsByUserId(UUID id) {
        return null;
    }

    @Override
    public void clearData() {

    }
}
