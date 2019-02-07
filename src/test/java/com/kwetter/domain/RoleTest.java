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
    private Role testRoleEmpty;
    private UUID testId;
    private String testName;

    @Before
    public void setup() {
        testId = UUID.randomUUID();
        testName = "ThisIsATest";
        testRole = new Role(testId, testName);
        testRoleEmpty = new Role();
    }

    @Test
    public void getIdTest() {
        assertEquals("Getting the Id of a role failed.", testId, testRole.getId());
    }

    @Test
    public void getIdEmptyTest() {
        assertEquals("The id was not empty.", null, testRoleEmpty.getId());
    }

    @Test
    public void getNameTest() {
        assertEquals("Getting the Name of a role failed.", testName, testRole.getName());
    }

    @Test
    public void getNameEmptyTest() {
        assertEquals("The name was not empty.", null, testRoleEmpty.getName());
    }
}