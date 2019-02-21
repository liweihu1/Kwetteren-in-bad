package com.kwetter.dao.memory;

import com.kwetter.dao.database.MemoryDatabase;
import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.domain.Kweet;
import com.kwetter.domain.User;

import javax.ejb.EJB;
import javax.enterprise.inject.Alternative;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Alternative
public class UserDAOMEMImpl implements UserDAO {

    @EJB
    private MemoryDatabase database;

    public UserDAOMEMImpl(){

    }

    @Override
    public User add(User user) {
        try{
            database.getUsers().add(user);
            return user;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(User user) {
        try{
            database.getUsers().remove(user);
        } catch (Exception e){
            // TODO LOGGER
        }
    }

    @Override
    public boolean checkUsernameAvailable(String username) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return database.getUsers();
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User findById(UUID id) {
        return database.getUserById(id);
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public Set<User> getFollowing(UUID id) {
        return null;
    }

    @Override
    public Set<User> getFollowers(UUID id) {
        return null;
    }

    @Override
    public List<Kweet> getUserMentions(User user) {
        return null;
    }

    @Override
    public void clearData() {
        database.clearData();
    }
}
