package com.kwetter.dao.JPA;

import com.kwetter.dao.interfaces.UserDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDAOJPAImpl implements UserDAO {
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    
}
