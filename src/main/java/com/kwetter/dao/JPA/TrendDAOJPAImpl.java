package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.TrendDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TrendDAOJPAImpl implements TrendDAO {
    @PersistenceContext
    private EntityManager em;
}
