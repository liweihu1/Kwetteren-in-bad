package com.kwetter.dao.interfaces;

import com.kwetter.domain.Tweet;
import com.kwetter.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserDAO {
    /**
     * Add a new user to the database.
     * @param user The user to persist.
     * @return the user if it passed else return null.
     */
    User add(User user);

    /**
     * Deletes the given user from the database.
     * @param user The user to delete.
     */
    void delete(User user);

    /**
     * Updates the information of a user.
     * @param user The updated user.
     * @return the user if it succeeded else return null.
     */
    User update(User user);

    /**
     * Finds an user with the given ID.
     * @param id The ID of the user.
     * @return the User if found, else return null.
     */
    User findById(UUID id);

    /**
     * Get the following list of the given user ID.
     * @param id The Id of the user.
     * @return A list of all the people they're following.
     */
    List<User> getFollowing(UUID id);

    /**
     * Get the followers list of the given user ID.
     * @param id The Id of the user.
     * @return A list of all the people that follow the user.
     */
    List<User> getFollowers(UUID id);

    /**
     * Get all the tweets that the user has been mentioned in.
     * @param user The user to find.
     * @return A list of all the tweets the user is mentioned in.
     */
    List<Tweet> getUserMentions(User user);

    /**
     * Clears the data.
     */
    void clearData();
}
