package com.kwetter.dao.interfaces;

import com.kwetter.dao.JPA.KweetDAOJPAImpl;
import com.kwetter.dao.JPA.UserDAOJPAImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class KweetDAOJPAImplTest extends KweetDAOTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void setupOnce(){
        emf = Persistence.createEntityManagerFactory("kwetterTestPU");
    }

    @Before
    public void setup(){
        em = emf.createEntityManager();
        em.getTransaction().begin();

        KweetDAO kweetDAO = new KweetDAOJPAImpl();
        UserDAO userDAO = new UserDAOJPAImpl();

        kweetDAO.setEm(em);
        userDAO.setEm(em);

        setUserDAO(userDAO);
        setKweetDAO(kweetDAO);

        super.setup();
    }

    @After
    public void teardown(){
        try {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        super.teardown();
    }

    public static EntityManager getEm(){
        return em;
    }
}
