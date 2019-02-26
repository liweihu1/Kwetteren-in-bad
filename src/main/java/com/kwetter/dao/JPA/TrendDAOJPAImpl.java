package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.TrendDAO;
import com.kwetter.domain.Trend;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless
@Default
public class TrendDAOJPAImpl implements TrendDAO {
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    @Override
    public Trend add(Trend trend) {
        em.persist(trend);
        return trend;
    }

    @Override
    public void delete(Trend trend) {
        em.remove(trend);
    }

    @Override
    public Trend findById(UUID id) {
        return em.find(Trend.class, id);
    }

    @Override
    public Trend update(Trend trend) {
        return em.merge(trend);
    }

    @Override
    public void clearData() {
        em.clear();
    }
}
