package com.kwetter.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class RoleTest {
    private Role testRole;
    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private User user6;
    private User user7;
    private User user8;
    private User user9;
    private User user10;

    @Before
    public void setup() {
        UUID testUUID = UUID.randomUUID();
        String username = "JohnnyDoe";
        String firstName = "John";
        String lastName = "Doe";
        String biography = "HELLO THIS IS A TEST BIOGRAPHY TEST DEMO TEST DEMO";
        String website = "test.test.test";
        String location = "TestLocation 3";
        String password = "I'llYeetMyselfOffTheStairs";

        ArrayList tweets = new ArrayList(){{
            add(new Tweet(null, "TestMessage", new Date(), null, null, null));
        }};

        this.user1 = new User(testUUID, username + 1, password, firstName, lastName, biography, website, location,  new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Role>(), new ArrayList<Tweet>());

        this.user2 = new User(testUUID, username + 2, password, firstName, lastName, biography, website, location, new ArrayList<User>() {{
            add(user1);
        }}, new ArrayList<User>(), new ArrayList<Role>(), tweets);

        this.user3 = new User(testUUID, username + 3, password, firstName, lastName, biography, website, location,
                new ArrayList<User>() {{
                    add(user1);
                }}, new ArrayList<User>() {{
                    add(user2);
        }}, new ArrayList<Role>(), tweets);

        this.user4 = new User(testUUID, username + 4, password, firstName, lastName, biography, website, location, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Role>(), new ArrayList<Tweet>());

        this.user5 = new User(testUUID, username + 5, password, firstName, lastName, biography, website, location, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Role>(), new ArrayList<Tweet>());

        this.user6 = new User(testUUID, username + 6, password, firstName, lastName, biography, website, location, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Role>(), new ArrayList<Tweet>());

        this.user7 = new User(testUUID, username + 7, password, firstName, lastName, biography, website, location, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Role>(), new ArrayList<Tweet>());

        this.user8 = new User(testUUID, username + 8, password, firstName, lastName, biography, website, location, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Role>(), new ArrayList<Tweet>());

        this.user9 = new User(testUUID, username + 9, password, firstName, lastName, biography, website, location, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Role>(), new ArrayList<Tweet>());

        this.user10 = new User(testUUID, username + 10, password, firstName, lastName, biography, website, location, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Role>(), new ArrayList<Tweet>());
    }

    @Test
    public void getIdTest() {
    }

    @Test
    public void getNameTest() {
    }
}