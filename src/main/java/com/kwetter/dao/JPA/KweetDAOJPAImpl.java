package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.KweetDAO;
import com.kwetter.domain.Kweet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Stateless
public class KweetDAOJPAImpl implements KweetDAO {
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public Kweet add(Kweet Kweet) {
        em.persist(Kweet);
        return Kweet;
    }

    @Override
    public void delete(Kweet Kweet) {
        em.remove(Kweet);
    }

    @Override
    public Kweet findById(UUID id) {
        return em.find(Kweet.class, id);
    }

    @Override
    public List<Kweet> getAllKweets() {
        return em.createNamedQuery("Kweet.getAllKweets", Kweet.class).getResultList();
    }

    @Override
    public List<Kweet> getKweetLikesForUserId(UUID id) {
        return null;
    }

    @Override
    public List<Kweet> getAllKweetsByUserId(UUID id) {
        //TODO ADD NAMED QUERY FOR THIS
        return null;
    }

    @Override
    public List<Kweet> getLatestKweetsForUserId(UUID id) {
        return em.createNamedQuery("Kweet.getLatestForUser", Kweet.class).setParameter("userId", id).getResultList();
    }

    @Override
    public List<Kweet> getKweetThatContainsSearch(String search) {
        return em.createNamedQuery("Kweet.getKweetWithMessage", Kweet.class).setParameter("search", "%" + search + "%").getResultList();
    }

    @Override
    public Kweet update(Kweet Kweet) {
        return em.merge(Kweet);
    }

    @Override
    public void clearData() {
        em.clear();
    }
}
