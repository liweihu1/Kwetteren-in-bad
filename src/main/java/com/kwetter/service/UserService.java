package com.kwetter.service;

import com.kwetter.dao.interfaces.UserDAO;
import com.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Stateless
public class UserService {
    @Inject
    private UserDAO userDAO;

    public User getUserById(UUID id){
        return userDAO.findById(id);
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public void createUser(User user){
        this.userDAO.add(user);
    }
}
