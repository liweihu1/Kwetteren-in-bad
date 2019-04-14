package com.kwetter.service;

import com.kwetter.dao.JPA.AuthDAOJPAImpl;
import com.kwetter.dao.JPA.KweetDAOJPAImpl;
import com.kwetter.dao.JPA.UserDAOJPAImpl;
import com.kwetter.dao.database.MemoryDatabase;
import com.kwetter.dao.interfaces.AuthDAO;
import com.kwetter.dao.interfaces.KweetDAO;
import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.dao.memory.KweetDAOMEMImpl;
import com.kwetter.dao.memory.UserDAOMEMImpl;
import com.kwetter.domain.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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

    @Inject
    UserService userService;

    private Kweet testKweet1;
    private Kweet testKweet2;
    private User testUser;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(KweetService.class, Kweet.class, KweetDAO.class, UserDAO.class, User.class, Trend.class, Role.class, KweetDAOMEMImpl.class, KweetDAOJPAImpl.class, UserDAOMEMImpl.class, UserDAOJPAImpl.class, MemoryDatabase.class, UserService.class, AuthDAO.class, AuthDAOJPAImpl.class, Token.class)
                .addPackages(true, KweetService.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void startup(){
        this.testUser = new User(UUID.randomUUID(), "Test", "Test","Test", "Test", "Test", "Test", "Test", new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.testUser = this.userService.createUser(testUser);
        this.testKweet1 = new Kweet(UUID.randomUUID(), testUser, "Just imagine this test message being really cool.", new Date(), new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),0);
        this.testKweet2 = new Kweet(UUID.randomUUID(), testUser, "Now this is a test message.", new Date(), new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),0);
    }

    @After
    public void teardown(){
        kweetService.removeKweetById(testKweet1.getId());
        userService.deleteUserById(testUser.getId());
    }

    @Test
    public void getKweetsForUserId() {
        testKweet1 = this.kweetService.createKweet(testKweet1.getMessage(), testKweet1.getAuthor().getId().toString());
        assertEquals("Test user did not have any kweets", 1,  this.kweetService.getKweetsForUserId(testUser.getId(),0 , 10).size());
    }

    @Test
    public void getKweetById() {
        testKweet1 = this.kweetService.createKweet(testKweet1.getMessage(), testKweet1.getAuthor().getId().toString());
        assertEquals("Test kweet was not added", testKweet1.getId(), kweetService.getKweetById(testKweet1.getId()).getId());
    }

    @Test
    public void createKweet() {
        testKweet1 = this.kweetService.createKweet(testKweet1.getMessage(), testKweet1.getAuthor().getId().toString());
        assertNotNull("Test kweet was not created", testKweet1);
    }

    @Test
    public void getAllKweets() {
        testKweet1 = this.kweetService.createKweet(testKweet1.getMessage(), testKweet1.getAuthor().getId().toString());
        testKweet2 = this.kweetService.createKweet(testKweet2.getMessage(), testKweet2.getAuthor().getId().toString());
        assertEquals("Not all kweets have been added", 2, this.kweetService.getAllKweets().size());
        kweetService.removeKweetById(testKweet2.getId());
    }

    @Test
    public void getKweetsBySearchString() {
        testKweet1 = this.kweetService.createKweet(testKweet1.getMessage(), testKweet1.getAuthor().getId().toString());
        testKweet2 = this.kweetService.createKweet(testKweet2.getMessage(), testKweet2.getAuthor().getId().toString());
        assertEquals("There were no kweets found.", 2, this.kweetService.getKweetsBySearchString("test",0 , 10).size());
        kweetService.removeKweetById(testKweet2.getId());
    }

    @Test
    public void getKweetsByUserIdWithFollowing() {
        testKweet1 = this.kweetService.createKweet(testKweet1.getMessage(), testKweet1.getAuthor().getId().toString());
        assertEquals("No kweets were found", 1, kweetService.getKweetsByUserIdWithFollowing(testUser.getId(),0 , 10).size());
    }

    @Test
    public void removeKweetById() {
        testKweet1 = this.kweetService.createKweet(testKweet1.getMessage(), testKweet1.getAuthor().getId().toString());
        testKweet2 = this.kweetService.createKweet(testKweet2.getMessage(), testKweet2.getAuthor().getId().toString());
        this.kweetService.removeKweetById(testKweet2.getId());
        assertNull("Test kweet 2 was not removed.", this.kweetService.getKweetById(testKweet2.getId()));
    }
}
