package com.kwetter.dao.interfaces;

import com.kwetter.domain.Tweet;

import java.util.List;
import java.util.UUID;

public interface TweetDAO {
    /**
     * Adds the tweet to the database.
     * @param tweet tweet to persist.
     * @return Return the tweet.
     */
    Tweet add(Tweet tweet);

    /**
     * Delete the given tweet from the database.
     * @param tweet Tweet to delete.
     */
    void delete(Tweet tweet);

    /**
     * Update the given tweet.
     * @param tweet the tweet to update.
     * @return the updated tweet.
     */
    Tweet update(Tweet tweet);

    /**
     * Find tweet by id;
     * @param id The tweet id.
     * @return tweet found by id.
     */
    Tweet findById(UUID id);

    /**
     * Get all the tweets.
     * @return all the tweets.
     */
    List<Tweet> getAllTweets();

    /**
     * Get liked tweets for the given user id.
     * @param id the id of the user.
     * @return List of tweets that were liked by the user.
     */
    List<Tweet> getTweetLikesForUserId(UUID id);

    /**
     * Get all the tweets for the user.
     * @param id the id of the user.
     * @return List of tweets.
     */
    List<Tweet> getAllTweetsByUserId(UUID id);

    /**
     * Returns a list with the 10 most recent tweets.
     * @param id The id of the user.
     * @return List with 10 tweets.
     */
    List<Tweet> getLatestTweetsForUserId(UUID id);

    /**
     * Return a list with the tweets that contain the search string.
     * @param search String to search for.
     * @return List of tweets.
     */
    List<Tweet> getTweetThatContainsSearch(String search);

    /**
     * Clears the data.
     */
    void clearData();
}
