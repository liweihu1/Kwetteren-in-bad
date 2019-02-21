package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.domain.Kweet;
import com.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
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
    public boolean checkUsernameAvailable(String username) {
        List<User> u = em.createNamedQuery("user.findByUsername", User.class).setParameter("username", username).getResultList();
        return u.size() == 0;
    }

    @Override
    public List<User> getAllUsers() {
        return em.createNamedQuery("user.getAllUsers", User.class).getResultList();
    }

    @Override
    public User findById(UUID id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        return em.createNamedQuery("user.findByUsername", User.class).setParameter("username", username).getSingleResult();
    }

    @Override
    public Set<User> getFollowing(UUID id) {
        return em.find(User.class, id).getFollowing();
    }

    @Override
    public Set<User> getFollowers(UUID id) {
        return em.find(User.class, id).getFollowers();
    }

    @Override
    public List<Kweet> getUserMentions(User user) {
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
