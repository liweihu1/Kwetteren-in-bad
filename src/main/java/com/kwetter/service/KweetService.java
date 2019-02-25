package com.kwetter.service;

import com.kwetter.dao.interfaces.KweetDAO;
import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.domain.Kweet;
import com.kwetter.domain.Trend;
import com.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class KweetService {
    @Inject
    private KweetDAO kweetDAO;

    @Inject
    private UserDAO userDAO;

    public List<Kweet> getKweetsForUserId(UUID userId){
        return kweetDAO.getLatestKweetsForUserId(userId);
    }

    public Kweet getKweetById(UUID id){
        return kweetDAO.findById(id);
    }

    public boolean createKweet(Kweet kweet){
        kweet.getMentions().addAll(getMentionsForKweet(kweet.getMessage()));
        return kweetDAO.add(kweet) != null;
    }

    public List<Kweet> getAllKweets(){
        return kweetDAO.getAllKweets();
    }

    public List<Kweet> getKweetsBySearchString(String search){
        return kweetDAO.getKweetThatContainsSearch(search);
    }

    public List<Kweet> getKweetsByUserIdWithFollowing(UUID id){
        return kweetDAO.getKweetForUserIdWithFollowers(id);
    }

    public Kweet removeKweetById(UUID id) {
        Kweet kweetToDelete = kweetDAO.findById(id);
        kweetDAO.delete(kweetToDelete);
        return kweetToDelete;
    }

    private List<User> getMentionsForKweet(String message) {
        Pattern pattern = Pattern.compile("(#(\\\\w*[0-9a-zA-Z]))");
        Matcher matcher = pattern.matcher(message);
        List<String> usernames = new ArrayList<>();
        while(matcher.find()){
            usernames.add(matcher.group());
        }
        List<User> result = new ArrayList<>();
        for(String username : usernames){
            result.add(userDAO.findByUsername(username));
        }
        return result;
    }

    private List<Trend> getTrendsForKweet(String message) {
        Pattern pattern = Pattern.compile("(@(\\\\w*[0-9a-zA-Z]))");
        Matcher matcher = pattern.matcher(message);
        List<String> usernames = new ArrayList<>();
        while(matcher.find()){
            usernames.add(matcher.group());
        }
        List<User> result = new ArrayList<>();
        for(String username : usernames){
            result.add(userDAO.findByUsername(username));
        }
        return result;
    }
}
