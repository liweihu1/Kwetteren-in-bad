package com.kwetter.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

public class TweetTest {
    private Tweet testTweet;
    private Tweet testTweetEmpty;
    private User testUser;
    private UUID testId;
    private String testMessage;
    private Date testDate;

    @Before
    public void setup() {
        testId = UUID.randomUUID();
        testUser = new User(UUID.randomUUID(), "Test", "Test", "Test bio", "Reee", "Skkrt", new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Role>(), new ArrayList<Tweet>(), new ArrayList<Tweet>(), new ArrayList<Tweet>());
        testMessage = "This is a test lol";
        testDate = new Date();
        testTweetEmpty = new Tweet();
        testTweet = new Tweet(testId, testUser, testMessage, testDate, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Trend>());
    }

    @Test
    public void getIdTest() {
        assertEquals("The id was not right.", this.testId, testTweet.getId());
    }

    @Test
    public void getEmptyIdTest() {
        assertNull("The id was not empty.", testTweetEmpty.getId());
    }

    @Test
    public void getMessageTest() {
        assertEquals("The message was not right", this.testMessage, testTweet.getMessage());
    }

    @Test
    public void getEmptyMessageTest() {
        assertNull("The message was not right", testTweetEmpty.getMessage());
    }

    @Test
    public void getAuthorTest() {
        assertEquals("The author was not right.", this.testUser, testTweet.getAuthor());
    }

    @Test
    public void getEmptyAuthorTest() {
        assertNull("The author was not empty.", testTweetEmpty.getAuthor());
    }

    @Test
    public void getMentionsTest() {
        assertNotNull("The mentions was null.", testTweet.getMentions());
    }

    @Test
    public void getEmptyMentionsTest() {
        assertNull("The mentions was not null.", testTweetEmpty.getMentions());
    }

    @Test
    public void getHeartedByTest() {
        assertNotNull("The hearted by list was null.", testTweet.getHeartedBy());
    }

    @Test
    public void getEmptyHeartedByTest() {
        assertNull("The hearted by list was not null.", testTweetEmpty.getHeartedBy());
    }

    @Test
    public void getDateCreatedTest() {
        assertEquals("The Date was not right.", this.testDate, testTweet.getDateCreated());
    }

    @Test
    public void getEmptyDateCreatedTest() {
        assertNull("The Date was not right.", testTweetEmpty.getDateCreated());
    }

    @Test
    public void getTrendsTest() {
        assertNotNull("The trends list was null.", testTweet.getTrends());
    }

    @Test
    public void getEmptyTrendsTest() {
        assertNull("The trends list was not null.", testTweetEmpty.getTrends());
    }
}