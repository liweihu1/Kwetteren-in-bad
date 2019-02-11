package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.TweetDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TweetDAOJPAImpl implements TweetDAO {
    @PersistenceContext
    private EntityManager em;
}
