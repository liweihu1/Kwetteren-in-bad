package com.kwetter.dao.interfaces;

import com.kwetter.domain.Tweet;

import java.util.List;
import java.util.UUID;

public interface TweetDAO {
    Tweet add(Tweet tweet);
    void delete(Tweet tweet);
    Tweet update(Tweet tweet);
    Tweet findById(UUID id);
    List<Tweet> getAllTweets();
    List<Tweet> getTweetLikes(UUID id);
    List<Tweet> getTweetByUserId(UUID id);
    void clearData();
}
