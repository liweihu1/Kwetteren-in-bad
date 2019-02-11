package com.kwetter.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class TrendTest {
    private UUID testId;
    private Trend testTrend;
    private Trend testEmptyTrend;
    private List<Tweet> testTweets;
    private String testName;

    @Before
    public void setup(){
        this.testId = UUID.randomUUID();
        this.testTweets = new ArrayList<>();
        this.testName = "#Test";
        this.testTrend = new Trend(testId, testName, testTweets);
        this.testEmptyTrend = new Trend();
    }

    @Test
    public void getIdTest(){
        assertEquals("The ID was not right.", this.testId, testTrend.getId());
    }

    @Test
    public void getEmptyIdTest(){
        assertNull("The ID was not right.", testEmptyTrend.getId());
    }


    @Test
    public void getNameTest() {
        assertEquals("The name was not right.", testName, this.testTrend.getName());
    }

    @Test
    public void getEmptyNameTest() {
        assertNull("The name was not empty.", this.testEmptyTrend.getName());
    }

    @Test
    public void getTweetsTest() {
        assertNotNull("The tweet list was null.", testTrend.getTweets());
    }

    @Test
    public void getEmptyTweetsTest() {
        assertNull("The tweet list was not null.", testEmptyTrend.getTweets());
    }
}