package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.domain.Kweet;
import com.kwetter.domain.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Stateless
@Default
public class UserDAOJPAImpl implements UserDAO {
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    public UserDAOJPAImpl() {

    }

    @Override
    public User add(User user) {
        try {
            em.persist(user);
            return user;
        } catch (Exception e){
            return null;
        }
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
        try {
            return em.createNamedQuery("user.getAllUsers", User.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User findById(UUID id) {
        try {
            return em.find(User.class, id);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            return em.createNamedQuery("user.findByUsername", User.class).setParameter("username", username).getSingleResult();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Kweet> getUserMentions(User user) {
        //TODO ADD NAMED QUERY FOR THIS
        return null;
    }

    @Override
    public List<User> getFollowersForUserWithId(UUID id) {
        try {
            return em.createNamedQuery("user.getFollowersForId", User.class).setParameter("userId", id).getResultList();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<User> getFollowingForUserWithId(UUID id) {
        try {
            return em.createNamedQuery("user.getFollowingForId", User.class).setParameter("userId", id).getResultList();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public User update(User user) {
        try {
            return em.merge(user);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void clearData() {
        if(em.getTransaction().isActive()){
            em.clear();
        }
    }

    @Override
    public void setEm(EntityManager em){
        this.em = em;
    }
}
