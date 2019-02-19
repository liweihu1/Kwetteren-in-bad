package com.kwetter.dao.database;

import com.kwetter.domain.Trend;
import com.kwetter.domain.Tweet;
import com.kwetter.domain.User;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
public class MemoryDatabase {
    protected List<User> users;
    protected List<Tweet> tweets;
    protected List<Trend> trends;

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

    public User getUserById(UUID id){
        return this.users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
}
