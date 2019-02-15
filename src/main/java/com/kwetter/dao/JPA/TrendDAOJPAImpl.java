package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.TrendDAO;
import com.kwetter.domain.Trend;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless
public class TrendDAOJPAImpl implements TrendDAO {
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    public Trend addToDatabase(Trend trend) {
        em.persist(trend);
        return trend;
    }

    public void delete(Trend trend) {
        em.remove(trend);
    }

    public Trend get(UUID id) {
        return em.find(Trend.class, id);
    }

    public Trend update(Trend trend) {
        return em.merge(trend);
    }

    public void clearData() {
        em.clear();
    }
}
