package com.kwetter.dao.memory;

import com.kwetter.dao.database.MemoryDatabase;
import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.domain.Kweet;
import com.kwetter.domain.User;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Stateless
@Named("UserDAOMEMImpl")
public class UserDAOMEMImpl implements UserDAO {
    private MemoryDatabase database;

    public UserDAOMEMImpl(){
        this.database = MemoryDatabase.getInstance();
    }

    @Override
    public User add(User user) {
        try{
            System.out.println("Memory database in use");
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
    public List<Kweet> getUserMentions(User user) {
        return null;
    }

    @Override
    public List<User> getFollowersForUserWithId(UUID id) {
        return null;
    }

    @Override
    public List<User> getFollowingForUserWithId(UUID id) {
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
