package com.kwetter.service;

import com.kwetter.dao.interfaces.AuthDAO;
import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.domain.Token;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

@Stateless
public class AuthService {
    @Inject
    private AuthDAO authDAO;

    @Inject
    private UserDAO userDAO;

    public Token getTokenForUser(UUID userId){
        Token result = authDAO.findTokenForUser(userId);
        if (result == null){
            result = authDAO.addToken(new Token(userDAO.findById(userId)));
        }
        return result;
    }
}
