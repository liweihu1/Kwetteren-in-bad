package com.kwetter.dao.database;

import com.kwetter.domain.Trend;
import com.kwetter.domain.Tweet;
import com.kwetter.domain.User;

import java.util.ArrayList;
import java.util.List;

public class MemoryDatabase {
    private static MemoryDatabase database;

    protected List<User> users;
    protected List<Tweet> tweets;
    protected List<Trend> trends;

    public static MemoryDatabase getInstance(){
        if (database == null){
            database = new MemoryDatabase();
        }
        return database;
    }

    public void clearData(){
        this.users = new ArrayList<>();
        this.tweets = new ArrayList<>();
        this.trends = new ArrayList<>();
    }

    public List<User> getUsers() {
        return this.users;
    }

    public List<Tweet> getTweets() {
        return this.tweets;
    }

    public List<Trend> getTrends() {
        return this.trends;
    }
}
