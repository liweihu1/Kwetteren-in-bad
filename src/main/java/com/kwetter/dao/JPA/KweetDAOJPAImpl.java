package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.KweetDAO;
import com.kwetter.domain.Kweet;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Stateless
@Default
public class KweetDAOJPAImpl implements KweetDAO {
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public Kweet add(Kweet kweet) {
        try {
            em.persist(kweet);
            return kweet;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(Kweet kweet) {
        em.remove(kweet);
    }

    @Override
    public Kweet findById(UUID id) {
        try {
            return em.find(Kweet.class, id);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Kweet> getAllKweets() {
        try {
            return em.createNamedQuery("kweet.getAllKweets", Kweet.class).getResultList();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Kweet> getKweetLikesForUserId(UUID id) {
        return null;
    }

    @Override
    public List<Kweet> getAllKweetsByUserId(UUID id) {
        try {
            return em.createNamedQuery("kweet.getLatestForUser", Kweet.class).setParameter("userId", id).getResultList();
        } catch (Exception e){
            return null;
        }

    }

    @Override
    public List<Kweet> getKweetForUserIdWithFollowers(UUID id) {
        try {
            return em.createNamedQuery("kweet.getKweetForUserAndFollowing", Kweet.class).setParameter("userId", id).getResultList();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Kweet> getLatestKweetsForUserId(UUID id) {
        try {
            return em.createNamedQuery("kweet.getLatestForUser", Kweet.class).setMaxResults(10).setParameter("userId", id).getResultList();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Kweet> getKweetThatContainsSearch(String search) {
        try {
            return em.createNamedQuery("kweet.getKweetWithMessage", Kweet.class).setParameter("search", "%" + search + "%").getResultList();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Kweet update(Kweet Kweet) {
        try {
            return em.merge(Kweet);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void clearData() {
        em.clear();
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
