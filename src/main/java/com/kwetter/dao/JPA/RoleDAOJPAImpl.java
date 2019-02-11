package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.RoleDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RoleDAOJPAImpl implements RoleDAO {
    @PersistenceContext
    private EntityManager em;
}
