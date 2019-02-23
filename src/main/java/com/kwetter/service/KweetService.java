package com.kwetter.service;

import com.kwetter.dao.interfaces.KweetDAO;
import com.kwetter.domain.Kweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Stateless
public class KweetService {
    @Inject
    private KweetDAO kweetDAO;

    public List<Kweet> getKweetsForUserId(UUID userId){
        return kweetDAO.getLatestKweetsForUserId(userId);
    }

    public Kweet getKweetById(UUID id){
        return kweetDAO.findById(id);
    }

    public boolean createKweet(Kweet Kweet){
        return kweetDAO.add(Kweet) != null;
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
}
