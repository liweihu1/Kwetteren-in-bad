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
import java.util.stream.Collectors;

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
        database.getUsers().remove(user);
    }

    @Override
    public boolean checkUsernameAvailable(String username) {
        return database.getUsers().stream().filter(user -> user.getUsername().equals(username)).findAny().orElse(null) == null;
    }

    @Override
    public List<User> getAllUsers() {
        return database.getUsers();
    }

    @Override
    public User update(User user) {
        User dbUser = database.getUsers().stream().filter(f -> f.getId() == user.getId()).findAny().orElse(null);
        if (dbUser != null) {
            database.getUsers().remove(dbUser);
            database.getUsers().add(user);
            return user;
        }
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
        return database.getUsers().stream().filter(f -> f.getId() != id && f.getFollowing().stream().filter(f2 -> f2.getId() == id).findAny().orElse(null) != null).collect(Collectors.toList());
    }

    @Override
    public List<User> getFollowingForUserWithId(UUID id) {
        return database.getUsers().stream().filter(f -> f.getId() != id && f.getFollowers().stream().filter(f2 -> f2.getId() == id).findAny().orElse(null) != null).collect(Collectors.toList());
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
