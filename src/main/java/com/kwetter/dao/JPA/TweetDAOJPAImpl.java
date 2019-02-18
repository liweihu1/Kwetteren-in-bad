package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.TweetDAO;
import com.kwetter.domain.Tweet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Stateless
public class TweetDAOJPAImpl implements TweetDAO {
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public Tweet add(Tweet tweet) {
        em.persist(tweet);
        return tweet;
    }

    @Override
    public void delete(Tweet tweet) {
        em.remove(tweet);
    }

    @Override
    public Tweet findById(UUID id) {
        return em.find(Tweet.class, id);
    }

    @Override
    public List<Tweet> getAllTweets() {
        //TODO ADD NAMED QUERY FOR THIS
        return null;
    }

    @Override
    public List<Tweet> getTweetLikesForUserId(UUID id) {
        return null;
    }

    @Override
    public List<Tweet> getAllTweetsByUserId(UUID id) {
        //TODO ADD NAMED QUERY FOR THIS
        return null;
    }

    @Override
    public List<Tweet> getLatestTweetsForUserId(UUID id) {
        return null;
    }

    @Override
    public Tweet update(Tweet tweet) {
        return em.merge(tweet);
    }

    @Override
    public void clearData() {
        em.clear();
    }
}
