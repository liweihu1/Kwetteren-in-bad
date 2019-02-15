package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.TweetDAO;
import com.kwetter.domain.Tweet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless
public class TweetDAOJPAImpl implements TweetDAO {
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    public Tweet addToDatabase(Tweet tweet) {
        em.persist(tweet);
        return tweet;
    }

    public void delete(Tweet tweet) {
        em.remove(tweet);
    }

    public Tweet get(UUID id) {
        return em.find(Tweet.class, id);
    }

    public Tweet update(Tweet tweet) {
        return em.merge(tweet);
    }

    public void clearData() {
        em.clear();
    }
}
