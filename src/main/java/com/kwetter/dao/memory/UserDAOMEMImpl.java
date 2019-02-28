package com.kwetter.dao.memory;

import com.kwetter.dao.database.MemoryDatabase;
import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.domain.Kweet;
import com.kwetter.domain.User;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Stateless
@Alternative
public class UserDAOMEMImpl implements UserDAO {
    private MemoryDatabase database;

    public UserDAOMEMImpl(){
        this.database = MemoryDatabase.getInstance();
    }

    @Override
    public User add(User user) {
        try{
            database.getUsers().add(user);
            return user;
//            return database.getUserByUsername(user.getUsername());
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
        return database.getUserByUsername(username);
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

    @Override
    public void setEm(EntityManager em){
        // TODO
    }
}
