package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.AuthDAO;
import com.kwetter.domain.Token;
import com.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless
@Default
public class AuthDAOJPAImpl implements AuthDAO {
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public Token addToken(Token token) {
        try {
            em.persist(token);
            return token;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void removeToken(Token token) {
        em.remove(token);
    }

    @Override
    public Token findTokenForUser(UUID userId) {
        try{
            return em.createNamedQuery("token.getTokenByUserId", Token.class).setParameter("userId", userId).getResultList().get(0);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Token login(String username, String password) {
        try {
            User u = em.createNamedQuery("token.validateLogin", User.class).setParameter("username", username).setParameter("password", password).getSingleResult();
            if (u != null){
                return new Token(u);
            }
            return null;
        } catch (Exception e){
            return null;
        }
    }
}
