package com.kwetter.dao.interfaces;

import com.kwetter.domain.Token;

import java.util.UUID;

public interface AuthDAO {

    /**
     * Adds a new token to the database.
     * @param token the token to persist.
     * @return the persisted token.
     */
    Token addToken(Token token);

    /**
     * Removes token from database.
     * @param token the token to remove.
     */
    void removeToken(Token token);

    /**
     * Returns a token if the user has a valid token already.
     * @param userId the id of the user.
     * @return a token if found, else return null.
     */
    Token findTokenForUser(UUID userId);

    /**
     * Logs in with the given values and adds a new token.
     * @param username the username of the user.
     * @param password the password of the user.
     * @return Token if successful.
     */
    Token login(String username, String password);
}
