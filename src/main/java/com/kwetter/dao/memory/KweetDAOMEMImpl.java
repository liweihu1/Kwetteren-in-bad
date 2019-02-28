package com.kwetter.dao.memory;

import com.kwetter.dao.database.MemoryDatabase;
import com.kwetter.dao.interfaces.KweetDAO;
import com.kwetter.domain.Kweet;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Stateless
@Alternative
public class KweetDAOMEMImpl implements KweetDAO {

    private MemoryDatabase database;

    public KweetDAOMEMImpl(){
        this.database = MemoryDatabase.getInstance();
    }

    @Override
    public Kweet add(Kweet kweet) {
        database.getKweets().add(kweet);
        return kweet;
    }

    @Override
    public void delete(Kweet kweet) {

    }

    @Override
    public Kweet update(Kweet kweet) {
        return null;
    }

    @Override
    public Kweet findById(UUID id) {
        return database.getKweets().stream().filter(k -> k.getId() == id).findAny().orElse(null);
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

    @Override
    public void setEm(EntityManager em) {
        // TODO
    }
}
