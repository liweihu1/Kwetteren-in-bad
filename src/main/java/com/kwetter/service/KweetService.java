package com.kwetter.service;

import com.kwetter.dao.interfaces.KweetDAO;
import com.kwetter.domain.Kweet;
import com.kwetter.dto.KweetDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Stateless
public class KweetService {
    @Inject
    private KweetDAO KweetDAO;

    public List<Kweet> getKweetsForUserId(UUID userId){
        return KweetDAO.getLatestKweetsForUserId(userId);
    }

    public Kweet getKweetById(UUID id){
        return KweetDAO.findById(id);
    }

    public boolean createKweet(Kweet Kweet){
        return KweetDAO.add(Kweet) != null;
    }

    public List<Kweet> getAllKweets(){
        return KweetDAO.getAllKweets();
    }

    public List<Kweet> getKweetsBySearchString(String search){
        return KweetDAO.getKweetThatContainsSearch(search);
    }
}
