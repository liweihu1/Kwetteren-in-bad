package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.AuthDAO;
import com.kwetter.domain.Token;

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
        em.persist(token);
        return token;
    }

    @Override
    public void removeToken(Token token) {
        em.remove(token);
    }

    @Override
    public Token findTokenForUser(UUID userId) {
        return em.createNamedQuery("token.getTokenByUserId", Token.class).setParameter("userId", userId).getResultList().get(0);
    }
}
