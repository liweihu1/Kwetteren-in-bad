package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.TrendDAO;
import com.kwetter.domain.Trend;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless
@Named("trendDAOJPA")
public class TrendDAOJPAImpl implements TrendDAO {
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public Trend add(Trend trend) {
        try {
            em.persist(trend);
            return trend;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(Trend trend) {
        em.remove(trend);
    }

    @Override
    public Trend findById(UUID id) {
        try {
            return em.find(Trend.class, id);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Trend update(Trend trend) {
        try {
            return em.merge(trend);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void clearData() {
        em.clear();
    }
}
