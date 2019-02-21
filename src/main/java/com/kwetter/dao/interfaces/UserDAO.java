package com.kwetter.dao.interfaces;

import com.kwetter.domain.Kweet;
import com.kwetter.domain.User;

import java.util.List;
import java.util.Set;
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
     * Checks if the username is available.
     * @param username the username to check.
     * @return true or false.
     */
    boolean checkUsernameAvailable(String username);

    /**
     * Gets all the users from the database.
     * @return all the users.
     */
    List<User> getAllUsers();

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
     * Find user by username;
     * @param username the username of the user;
     * @return user if found.
     */
    User findByUsername(String username);

    /**
     * Get the following list of the given user ID.
     * @param id The Id of the user.
     * @return A list of all the people they're following.
     */
    Set<User> getFollowing(UUID id);

    /**
     * Get the followers list of the given user ID.
     * @param id The Id of the user.
     * @return A list of all the people that follow the user.
     */
    Set<User> getFollowers(UUID id);

    /**
     * Get all the Kweets that the user has been mentioned in.
     * @param user The user to find.
     * @return A list of all the Kweets the user is mentioned in.
     */
    List<Kweet> getUserMentions(User user);

    /**
     * Clears the data.
     */
    void clearData();
}
