package com.kwetter.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class RoleTest {
    private User testUser;
    private User testAdministrator;
    private User testModerator;

    @Before
    public void setup() {
        testUser = new User(UUID.randomUUID(), "Test", "Test", "Test bio", "", "", null, null, new ArrayList<Role>() {{
            add(Role.Standard);
        }}, null, null, null);

        testAdministrator = new User(UUID.randomUUID(), "Test", "Test", "Test bio", "", "", null, null, new ArrayList<Role>() {{
            add(Role.Administrator);
        }}, null, null, null);

        testModerator = new User(UUID.randomUUID(), "Test", "Test", "Test bio", "", "", null, null, new ArrayList<Role>() {{
            add(Role.Moderator);
        }}, null, null, null);
    }

    @Test
    public void checkStandardRole() {
        assertEquals(Role.Standard, testUser.getRoles().get(0));
    }

    @Test
    public void checkAdministratorRole() {
        assertEquals(Role.Administrator, testAdministrator.getRoles().get(0));
    }

    @Test
    public void checkModeratorRole() {
        assertEquals(Role.Moderator, testModerator.getRoles().get(0));
    }
}