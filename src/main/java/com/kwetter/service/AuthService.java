package com.kwetter.service;

import com.kwetter.dao.interfaces.AuthDAO;
import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.domain.Token;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class AuthService {
    @EJB(beanName = "AuthDAOJPAImpl")
    private AuthDAO authDAO;

    @EJB(beanName = "UserDAOJPAImpl")
    private UserDAO userDAO;

    public Token getTokenForUser(UUID userId){
        Token result = authDAO.findTokenForUser(userId);
        if (result == null){
            result = authDAO.addToken(new Token(userDAO.findById(userId)));
        }
        return result;
    }

    public void removeTokenforUser(UUID userId){
        Token result = authDAO.findTokenForUser(userId);
        if (result != null){
            authDAO.removeToken(result);
        }
    }
}
