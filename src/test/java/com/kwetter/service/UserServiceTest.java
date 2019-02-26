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
import com.kwetter.dto.KweetDTO;
import com.kwetter.dto.TrendDTO;
import com.kwetter.dto.UserDTO;
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
import javax.transaction.Transactional;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UserServiceTest {
    @Inject
    private UserService userService;

    private User testUser1;
    private User testUser2;
    private User testUser3;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(KweetService.class, Kweet.class, KweetDAO.class, UserDAO.class, User.class, Trend.class, Role.class, KweetDAOMEMImpl.class, KweetDAOJPAImpl.class, UserDAOMEMImpl.class, UserDAOJPAImpl.class, MemoryDatabase.class, UserService.class, AuthDAO.class, AuthDAOJPAImpl.class, Token.class, UserDTO.class, KweetDTO.class, TrendDTO.class)
                .addPackages(true, KweetService.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setup(){
        this.testUser1 = new User(UUID.randomUUID(), "test1", "test1", "test1", "test1", "test1", "test1", new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.testUser2 = new User(UUID.randomUUID(), "test2", "test2", "test2", "test2", "test2", "test2", new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.testUser3 = new User(UUID.randomUUID(), "test3", "test3", "test3", "test3", "test3", "test3", new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        this.testUser1 = userService.createUser(testUser1);
        this.testUser2 = userService.createUser(testUser2);
        this.testUser3 = userService.createUser(testUser3);
    }

    @After
    public void teardown(){
        if (userService.getUserById(testUser3.getId()) != null){
            userService.deleteUserById(testUser3.getId());
        }

        if (userService.getUserById(testUser2.getId()) != null){
            userService.deleteUserById(testUser2.getId());
        }

        if (userService.getUserById(testUser1.getId()) != null){
            userService.deleteUserById(testUser1.getId());
        }
    }

    @Test
    public void getUserById() {
        assertEquals(testUser1.getUsername(), userService.getUserById(testUser1.getId()).getUsername());
    }

    @Test
    public void getAllUsers() {
        assertEquals(3, userService.getAllUsers().size());
    }

    @Test
    public void createUser() {
        assertNotNull(this.userService.createUser( new User(UUID.randomUUID(), "testUsername", "test3", "test3", "test3", "test3", "test3", new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>())));
    }

    @Test
    public void changeUsername() {
        String newUsername = "username1";
        this.userService.changeUsername(newUsername, testUser1.getId().toString());
        assertEquals(newUsername, this.userService.getUserById(testUser1.getId()).getUsername());
    }

    @Test
    public void deleteUserById() {
        this.userService.deleteUserById(testUser1.getId());
        assertNull(this.userService.getUserById(testUser1.getId()));
    }

    @Test
    public void followUserWithId() {
        this.userService.followUserWithId(testUser2.getId(), testUser1.getId());
        UserDTO testUser = new UserDTO(this.userService.getUserById(testUser2.getId()));
        assertEquals(1, testUser.getFollowing());
        testUser = new UserDTO(this.userService.getUserById(testUser1.getId()));
        assertEquals(1, testUser.getFollowers());
    }

    @Test
    @Transactional
    public void followUserWithUsername() {
        this.userService.followUserWithUsername(testUser2.getId(), testUser1.getUsername());
        UserDTO testUser = new UserDTO(this.userService.getUserById(testUser2.getId()));
        assertEquals(1, testUser.getFollowing());
        testUser = new UserDTO(this.userService.getUserById(testUser1.getId()));
        assertEquals(1, testUser.getFollowers());
    }

    @Test
    public void addRolesToUser() {
        this.userService.addRolesToUser(testUser1.getId(), new ArrayList<Role>() {{ add(Role.Moderator);}});
        assertTrue(this.userService.getUserById(testUser1.getId()).getRoles().contains(Role.Moderator));
    }

    @Test
    public void unFollowUserWithUsername() {
        this.userService.followUserWithUsername(testUser2.getId(), testUser1.getUsername());
        UserDTO testUser = new UserDTO(this.userService.getUserById(testUser1.getId()));
        assertEquals(1, testUser.getFollowing());
        this.userService.unFollowUserWithUsername(testUser2.getId(), testUser1.getUsername());
        testUser = new UserDTO(this.userService.getUserById(testUser1.getId()));
        assertEquals(0, testUser.getFollowing());
    }

    @Test
    public void unFollowUserWithId() {
        this.userService.followUserWithId(testUser2.getId(), testUser1.getId());
        UserDTO testUser = new UserDTO(this.userService.getUserById(testUser1.getId()));
        assertEquals(1, testUser.getFollowing());
        this.userService.unFollowUserWithId(testUser2.getId(), testUser1.getId());
        testUser = new UserDTO(this.userService.getUserById(testUser1.getId()));
        assertEquals(0, testUser.getFollowing());
    }
}