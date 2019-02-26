package com.kwetter.service;

import com.kwetter.dao.JPA.KweetDAOJPAImpl;
import com.kwetter.dao.JPA.UserDAOJPAImpl;
import com.kwetter.dao.database.MemoryDatabase;
import com.kwetter.dao.interfaces.KweetDAO;
import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.dao.memory.KweetDAOMEMImpl;
import com.kwetter.dao.memory.UserDAOMEMImpl;
import com.kwetter.domain.Kweet;
import com.kwetter.domain.Role;
import com.kwetter.domain.Trend;
import com.kwetter.domain.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class KweetServiceTest {
    @Inject
    KweetService kweetService;

    private Kweet testKweet;
    private User testUser;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(KweetService.class, Kweet.class, KweetDAO.class, UserDAO.class, User.class, Trend.class, Role.class, KweetDAOMEMImpl.class, KweetDAOJPAImpl.class, UserDAOMEMImpl.class, UserDAOJPAImpl.class, MemoryDatabase.class)
                .addPackages(true, KweetService.class.getPackage())
//                .addPackages(true, "org.hibernate")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void startup(){
        this.testUser = new User(UUID.randomUUID(), "Test", "Test", "Test", "Test", "Test", "Test", new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.testKweet = new Kweet(UUID.randomUUID(), testUser, "Test message swagggggg", new Date(), new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),0);
    }

    @Test
    public void getKweetsForUserId() {

    }

    @Test
    public void getKweetById() {

    }

    @Test
    public void createKweet() {

    }

    @Test
    public void getAllKweets() {
    }

    @Test
    public void getKweetsBySearchString() {
    }

    @Test
    public void getKweetsByUserIdWithFollowing() {
    }

    @Test
    public void removeKweetById() {
    }
}
