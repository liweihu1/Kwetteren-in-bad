package com.kwetter.dao.interfaces;

import com.kwetter.domain.Kweet;

import java.util.List;
import java.util.UUID;

public interface KweetDAO{
    /**
     * Adds the Kweet to the database.
     * @param Kweet Kweet to persist.
     * @return Return the Kweet.
     */
    Kweet add(Kweet Kweet);

    /**
     * Delete the given Kweet from the database.
     * @param Kweet Kweet to delete.
     */
    void delete(Kweet Kweet);

    /**
     * Update the given Kweet.
     * @param Kweet the Kweet to update.
     * @return the updated Kweet.
     */
    Kweet update(Kweet Kweet);

    /**
     * Find Kweet by id;
     * @param id The Kweet id.
     * @return Kweet found by id.
     */
    Kweet findById(UUID id);

    /**
     * Get all the Kweets.
     * @return all the Kweets.
     */
    List<Kweet> getAllKweets();

    /**
     * Get liked Kweets for the given user id.
     * @param id the id of the user.
     * @return List of Kweets that were liked by the user.
     */
    List<Kweet> getKweetLikesForUserId(UUID id);

    /**
     * Get all the Kweets for the user.
     * @param id the id of the user.
     * @return List of Kweets.
     */
    List<Kweet> getAllKweetsByUserId(UUID id);

    /**
     * Get all the kweets of the user and the users they are following.
     * @param id the id of the user.
     * @return List of kweets.
     */
    List<Kweet> getKweetForUserIdWithFollowers(UUID id);

    /**
     * Returns a list with the 10 most recent Kweets.
     * @param id The id of the user.
     * @return List with 10 Kweets.
     */
    List<Kweet> getLatestKweetsForUserId(UUID id);

    /**
     * Return a list with the Kweets that contain the search string.
     * @param search String to search for.
     * @return List of Kweets.
     */
    List<Kweet> getKweetThatContainsSearch(String search);

    /**
     * Clears the data.
     */
    void clearData();
}
