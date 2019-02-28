package com.kwetter.dao.interfaces;

import com.kwetter.domain.*;
import org.junit.Before;
import org.junit.Test;

public abstract class KweetDAOTest {

    private static KweetDAO kweetDAO;

    private Kweet testKweet1;
    private Kweet testKweet2;
    private Kweet testKweet3;

    protected static void setKweetDAO(KweetDAO kweetDAO) {
        KweetDAOTest.kweetDAO = kweetDAO;
    }


    @Before
    public void setup(){

    }

    @Test
    public void add() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void getAllKweets() {
    }

    @Test
    public void getKweetLikesForUserId() {
    }

    @Test
    public void getAllKweetsByUserId() {
    }

    @Test
    public void getKweetForUserIdWithFollowers() {
    }

    @Test
    public void getLatestKweetsForUserId() {
    }

    @Test
    public void getKweetThatContainsSearch() {
    }

    @Test
    public void clearData() {
    }
}