package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.AuthDAO;
import com.kwetter.domain.Token;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless
@Named("authDAOJPA")
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
        try{
            return em.createNamedQuery("token.getTokenByUserId", Token.class).setParameter("userId", userId).getResultList().get(0);
        } catch (Exception e){
            return null;
        }
    }
}
