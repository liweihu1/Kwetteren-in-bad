package com.kwetter.service;

import com.kwetter.dao.database.MemoryDatabase;
import com.kwetter.dao.interfaces.KweetDAO;
import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.dao.memory.KweetDAOMEMImpl;
import com.kwetter.dao.memory.UserDAOMEMImpl;
import com.kwetter.domain.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class KweetServiceTest {

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    private Kweet testKweet1;
    private Kweet testKweet2;
    private User testUser;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(UserService.class)
                .addClass(KweetService.class)
                .addClass(UserDAO.class)
                .addClass(UserDAOMEMImpl.class)
                .addClass(KweetDAO.class)
                .addClass(KweetDAOMEMImpl.class)
                .addClass(MemoryDatabase.class)
                .addClass(Kweet.class)
                .addClass(Trend.class)
                .addClass(User.class)
                .addClass(Role.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "WEB-INF/beans.xml");
    }

    @Before
    public void startup(){
        this.testUser = new User(UUID.randomUUID(), "Test", "Test","Test", "Test", "Test", "Test", "Test", new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.testUser = this.userService.createUser(testUser);
        this.testKweet1 = new Kweet(UUID.randomUUID(), testUser, "Just imagine this test message being really cool.", new Date(), new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),0);
        this.testKweet2 = new Kweet(UUID.randomUUID(), testUser, "Now this is a test message.", new Date(), new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),0);

        testUser.getKweets().add(testKweet1);
        testUser.getKweets().add(testKweet2);
        this.userService.updateUser(testUser);
    }

    @After
    public void teardown(){
        kweetService.removeKweetById(testKweet1.getId());
        userService.deleteUserById(testUser.getId());
    }

    @Test
    public void getKweetsForUserId() {
        List<Kweet> result = this.kweetService.getKweetsForUserId(testUser.getId(),0 , 10);
        assertEquals("Test user did not have any kweets", 2, result.size());
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
        this.testUser.getKweets().clear();
        testKweet1 = this.kweetService.createKweet(testKweet1.getMessage(), testKweet1.getAuthor().getId().toString());
        this.testUser.getKweets().add(testKweet1);
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
