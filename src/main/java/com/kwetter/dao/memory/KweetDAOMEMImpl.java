package com.kwetter.dao.memory;

import com.kwetter.dao.database.MemoryDatabase;
import com.kwetter.dao.interfaces.KweetDAO;
import com.kwetter.domain.Kweet;
import com.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        database.getKweets().stream().filter(k -> k.getId() == kweet.getId()).findAny().ifPresent(dbKweet -> database.getKweets().remove(dbKweet));
    }

    @Override
    public Kweet update(Kweet kweet) {
        Kweet dbKweet = database.getKweets().stream().filter(dbkweet -> dbkweet.getId() == kweet.getId()).findAny().orElse(null);
        if (dbKweet != null) {
            database.getKweets().remove(dbKweet);
            database.getKweets().add(kweet);
            return kweet;
        }
        return null;
    }

    @Override
    public Kweet findById(UUID id) {
        return database.getKweets().stream().filter(k -> k.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Kweet> getAllKweets() {
        return database.getKweets();
    }

    @Override
    public List<Kweet> getPaginatedKweets(int page, int count) {
        return database.getKweets();
    }

    @Override
    public List<Kweet> getLatestKweetsForUserId(UUID id, int page, int count) {
        User u = database.getUsers().stream().filter(f -> f.getId() == id).findAny().orElse(null);
        if ( u != null ){
            return u.getKweets();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Kweet> getKweetThatContainsSearch(String search, int page, int count) {
        return database.getKweets().stream().filter(k -> k.getMessage().contains(search)).collect(Collectors.toList());
    }

    @Override
    public List<Kweet> getKweetLikesForUserId(UUID id) {
        return null;
    }

    @Override
    public List<Kweet> getAllKweetsByUserId(UUID id, int page, int count) {
        User u = database.getUsers().stream().filter(f -> f.getId() == id).findAny().orElse(null);
        if ( u != null ){
            return u.getKweets();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Kweet> getKweetForUserIdWithFollowing(UUID id, int page, int count) {
        User u = database.getUsers().stream().filter(f -> f.getId() == id).findAny().orElse(null);
        if ( u != null ){
            List<Kweet> result = new ArrayList<>(u.getKweets());
            for (User user : database.getUserById(id).getFollowing()) {
                User dbUser = database.getUsers().stream().filter(f -> f.getId() == user.getId()).findAny().orElse(null);
                assert dbUser != null;
                result.addAll(dbUser.getKweets());
            }
            return result;
        }
        return new ArrayList<>();
    }

    @Override
    public void clearData() {
        database.clearData();
    }

    @Override
    public void setEm(EntityManager em) {
        // Don't set because there is no em
    }
}
