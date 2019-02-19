package com.kwetter.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.*;

public class TweetTest {
    private Tweet testTweet1;
    private Tweet testTweet2;
    private Tweet testTweet3;
    private Tweet testTweet4;
    private Tweet testTweet5;
    private Tweet testTweet6;
    private Tweet testTweet7;
    private Tweet testTweet8;
    private Tweet testTweet9;
    private Tweet testTweet10;
    private Tweet testTweetEmpty;
    private User testUser1;
    private User testUser2;
    private User testUser3;
    private User testUser4;
    private User testUser5;
    private User testUser6;
    private User testUser7;
    private User testUser8;
    private User testUser9;
    private User testUser10;
    private UUID testId;
    private String testMessage;
    private Date testDate;
    private Date testDate2;

    @Before
    public void setup() {
        testId = UUID.randomUUID();
        testMessage = "This is a test lol";
        testDate = new Date();
        testDate2 = new Date();
        testTweetEmpty = new Tweet();

        ArrayList<Tweet> testTweets = new ArrayList();
        ArrayList<User> testUsers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            if (i > 0){
                final User follower = testUsers.get(i - 1);
                testUsers.add(new User(UUID.randomUUID(), "Test" + i, "Test" + i, "Test", "Test bio", "geen site", "Skkrt", new HashSet(){{add(follower);}}, new HashSet<User>(), new ArrayList<Role>(), new ArrayList<Tweet>(), new ArrayList<Tweet>(), new ArrayList<Tweet>()));
            } else {
                testUsers.add(new User(UUID.randomUUID(), "Test" + i, "Test" + i, "Test", "Test bio", "geen site", "Skkrt", new HashSet<User>(), new HashSet<User>(), new ArrayList<>(), new ArrayList<Tweet>(), new ArrayList<Tweet>(), new ArrayList<Tweet>()));
            }
            testTweets.add(new Tweet(testId, testUsers.get(i), testMessage, testDate, testDate2, new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Trend>()));
        }

        testTweet1 = testTweets.get(0);
        testTweet2 = testTweets.get(1);
        testTweet3 = testTweets.get(2);
        testTweet4 = testTweets.get(3);
        testTweet5 = testTweets.get(4);
        testTweet6 = testTweets.get(5);
        testTweet7 = testTweets.get(6);
        testTweet8 = testTweets.get(7);
        testTweet9 = testTweets.get(8);
        testTweet10 = testTweets.get(9);

        testUser1 = testUsers.get(0);
        testUser2 = testUsers.get(1);
        testUser3 = testUsers.get(2);
        testUser4 = testUsers.get(3);
        testUser5 = testUsers.get(4);
        testUser6 = testUsers.get(5);
        testUser7 = testUsers.get(6);
        testUser8 = testUsers.get(7);
        testUser9 = testUsers.get(8);
        testUser10 = testUsers.get(9);
    }

    @Test
    public void getIdTest() {
        assertEquals("The id was not right for testTweet1.", this.testId, testTweet1.getId());
        assertEquals("The id was not right for testTweet2.", this.testId, testTweet2.getId());
        assertEquals("The id was not right for testTweet3.", this.testId, testTweet3.getId());
        assertEquals("The id was not right for testTweet4.", this.testId, testTweet4.getId());
        assertEquals("The id was not right for testTweet5.", this.testId, testTweet5.getId());
        assertEquals("The id was not right for testTweet6.", this.testId, testTweet6.getId());
        assertEquals("The id was not right for testTweet7.", this.testId, testTweet7.getId());
        assertEquals("The id was not right for testTweet8.", this.testId, testTweet8.getId());
        assertEquals("The id was not right for testTweet9.", this.testId, testTweet9.getId());
        assertEquals("The id was not right for testTweet10.", this.testId, testTweet10.getId());
    }

    @Test
    public void getEmptyIdTest() {
        assertNull("The id was not empty.", testTweetEmpty.getId());
    }

    @Test
    public void getMessageTest() {
        assertEquals("The message was not right for testTweet1", this.testMessage, testTweet1.getMessage());
        assertEquals("The message was not right for testTweet2", this.testMessage, testTweet2.getMessage());
        assertEquals("The message was not right for testTweet3", this.testMessage, testTweet3.getMessage());
        assertEquals("The message was not right for testTweet4", this.testMessage, testTweet4.getMessage());
        assertEquals("The message was not right for testTweet5", this.testMessage, testTweet5.getMessage());
        assertEquals("The message was not right for testTweet6", this.testMessage, testTweet6.getMessage());
        assertEquals("The message was not right for testTweet7", this.testMessage, testTweet7.getMessage());
        assertEquals("The message was not right for testTweet8", this.testMessage, testTweet8.getMessage());
        assertEquals("The message was not right for testTweet9", this.testMessage, testTweet9.getMessage());
        assertEquals("The message was not right for testTweet10", this.testMessage, testTweet10.getMessage());
    }

    @Test
    public void getEmptyMessageTest() {
        assertNull("The message was not right", testTweetEmpty.getMessage());
    }

    @Test
    public void getAuthorTest() {
        assertEquals("The author was not right for testTweet1.", this.testUser1, testTweet1.getAuthor());
        assertEquals("The author was not right for testTweet2.", this.testUser2, testTweet2.getAuthor());
        assertEquals("The author was not right for testTweet3.", this.testUser3, testTweet3.getAuthor());
        assertEquals("The author was not right for testTweet4.", this.testUser4, testTweet4.getAuthor());
        assertEquals("The author was not right for testTweet5.", this.testUser5, testTweet5.getAuthor());
        assertEquals("The author was not right for testTweet6.", this.testUser6, testTweet6.getAuthor());
        assertEquals("The author was not right for testTweet7.", this.testUser7, testTweet7.getAuthor());
        assertEquals("The author was not right for testTweet8.", this.testUser8, testTweet8.getAuthor());
        assertEquals("The author was not right for testTweet9.", this.testUser9, testTweet9.getAuthor());
        assertEquals("The author was not right for testTweet10.", this.testUser10, testTweet10.getAuthor());
    }

    @Test
    public void getEmptyAuthorTest() {
        assertNull("The author was not empty.", testTweetEmpty.getAuthor());
    }

    @Test
    public void getMentionsTest() {
        assertNotNull("The mentions was null testTweet1.", testTweet1.getMentions());
        assertNotNull("The mentions was null testTweet2.", testTweet2.getMentions());
        assertNotNull("The mentions was null testTweet3.", testTweet3.getMentions());
        assertNotNull("The mentions was null testTweet4.", testTweet4.getMentions());
        assertNotNull("The mentions was null testTweet5.", testTweet5.getMentions());
        assertNotNull("The mentions was null testTweet6.", testTweet6.getMentions());
        assertNotNull("The mentions was null testTweet7.", testTweet7.getMentions());
        assertNotNull("The mentions was null testTweet8.", testTweet8.getMentions());
        assertNotNull("The mentions was null testTweet9.", testTweet9.getMentions());
        assertNotNull("The mentions was null testTweet10.", testTweet10.getMentions());
    }

    @Test
    public void getEmptyMentionsTest() {
        assertNull("The mentions was not null.", testTweetEmpty.getMentions());
    }

    @Test
    public void getHeartedByTest() {
        assertNotNull("The hearted by list was null for testTweet1.", testTweet1.getHeartedBy());
        assertNotNull("The hearted by list was null for testTweet2.", testTweet2.getHeartedBy());
        assertNotNull("The hearted by list was null for testTweet3.", testTweet3.getHeartedBy());
        assertNotNull("The hearted by list was null for testTweet4.", testTweet4.getHeartedBy());
        assertNotNull("The hearted by list was null for testTweet5.", testTweet5.getHeartedBy());
        assertNotNull("The hearted by list was null for testTweet6.", testTweet6.getHeartedBy());
        assertNotNull("The hearted by list was null for testTweet7.", testTweet7.getHeartedBy());
        assertNotNull("The hearted by list was null for testTweet8.", testTweet8.getHeartedBy());
        assertNotNull("The hearted by list was null for testTweet9.", testTweet9.getHeartedBy());
        assertNotNull("The hearted by list was null for testTweet10.", testTweet10.getHeartedBy());
    }

    @Test
    public void getEmptyHeartedByTest() {
        assertNull("The hearted by list was not null.", testTweetEmpty.getHeartedBy());
    }

    @Test
    public void getDateCreatedTest() {
        assertEquals("The Date was not right for testTweet1.", testDate, testTweet1.getDateCreated());
        assertEquals("The Date was not right for testTweet2.", testDate, testTweet2.getDateCreated());
        assertEquals("The Date was not right for testTweet3.", testDate, testTweet3.getDateCreated());
        assertEquals("The Date was not right for testTweet4.", testDate, testTweet4.getDateCreated());
        assertEquals("The Date was not right for testTweet5.", testDate, testTweet5.getDateCreated());
        assertEquals("The Date was not right for testTweet6.", testDate, testTweet6.getDateCreated());
        assertEquals("The Date was not right for testTweet7.", testDate, testTweet7.getDateCreated());
        assertEquals("The Date was not right for testTweet8.", testDate, testTweet8.getDateCreated());
        assertEquals("The Date was not right for testTweet9.", testDate, testTweet9.getDateCreated());
        assertEquals("The Date was not right for testTweet10.", testDate, testTweet10.getDateCreated());
    }

    @Test
    public void getEmptyDateCreatedTest() {
        assertNull("The Date was not right.", testTweetEmpty.getDateCreated());
    }

    @Test
    public void getTrendsTest() {
        assertNotNull("The trends list was null for testTweet1.", testTweet1.getTrends());
        assertNotNull("The trends list was null for testTweet2.", testTweet2.getTrends());
        assertNotNull("The trends list was null for testTweet3.", testTweet3.getTrends());
        assertNotNull("The trends list was null for testTweet4.", testTweet4.getTrends());
        assertNotNull("The trends list was null for testTweet5.", testTweet5.getTrends());
        assertNotNull("The trends list was null for testTweet6.", testTweet6.getTrends());
        assertNotNull("The trends list was null for testTweet7.", testTweet7.getTrends());
        assertNotNull("The trends list was null for testTweet8.", testTweet8.getTrends());
        assertNotNull("The trends list was null for testTweet9.", testTweet9.getTrends());
        assertNotNull("The trends list was null for testTweet10.", testTweet10.getTrends());
    }

    @Test
    public void getEmptyTrendsTest() {
        assertNull("The trends list was not null.", testTweetEmpty.getTrends());
    }
}