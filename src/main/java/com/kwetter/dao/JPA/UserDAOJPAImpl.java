package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.domain.Tweet;
import com.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Stateless
public class UserDAOJPAImpl implements UserDAO {
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public User add(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public User findById(UUID id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getFollowing(UUID id) {
        //TODO ADD NAMED QUERY FOR THIS
        return null;
    }

    @Override
    public List<User> getFollowers(UUID id) {
        //TODO ADD NAMED QUERY FOR THIS
        return null;
    }

    @Override
    public List<Tweet> getUserMentions(User user) {
        //TODO ADD NAMED QUERY FOR THIS
        return null;
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public void clearData() {
        em.clear();
    }
}
