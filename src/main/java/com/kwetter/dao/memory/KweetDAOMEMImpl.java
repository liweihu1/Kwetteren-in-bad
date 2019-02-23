package com.kwetter.dao.memory;

import com.kwetter.dao.database.MemoryDatabase;
import com.kwetter.dao.interfaces.KweetDAO;
import com.kwetter.domain.Kweet;

import javax.ejb.EJB;
import javax.enterprise.inject.Alternative;
import java.util.List;
import java.util.UUID;

@Alternative
public class KweetDAOMEMImpl implements KweetDAO {

    @EJB
    private MemoryDatabase database;

    @Override
    public Kweet add(Kweet Kweet) {
        return null;
    }

    @Override
    public void delete(Kweet Kweet) {

    }

    @Override
    public Kweet update(Kweet Kweet) {
        return null;
    }

    @Override
    public Kweet findById(UUID id) {
        return null;
    }

    @Override
    public List<Kweet> getAllKweets() {
        return null;
    }

    @Override
    public List<Kweet> getLatestKweetsForUserId(UUID id) {
        return null;
    }

    @Override
    public List<Kweet> getKweetThatContainsSearch(String search) {
        return null;
    }

    @Override
    public List<Kweet> getKweetLikesForUserId(UUID id) {
        return null;
    }

    @Override
    public List<Kweet> getAllKweetsByUserId(UUID id) {
        return null;
    }

    @Override
    public List<Kweet> getKweetForUserIdWithFollowers(UUID id) {
        return null;
    }

    @Override
    public void clearData() {

    }
}
