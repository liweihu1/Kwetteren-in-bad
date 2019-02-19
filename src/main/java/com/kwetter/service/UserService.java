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

    public boolean changeUsername(String username, String userId){
        if (userDAO.checkUsernameAvailable(username)){
            User user = userDAO.findById(UUID.fromString(userId));
            if (user != null){
                user.setUsername(username);
                userDAO.update(user);
                return true;
            }
        }
        return false;
    }

    public boolean followUserWithId(UUID userId, UUID followId){
        User curUser = userDAO.findById(userId);
        User userToFollow = userDAO.findById(followId);
        return followUser(curUser, userToFollow);
    }

    public boolean followUserWithUsername(UUID userId, String username){
        User curUser = userDAO.findById(userId);
        User followUser = userDAO.findByUsername(username);
        return followUser(curUser, followUser);
    }

    private boolean followUser(User follower, User following){
        try {
            if (following != follower && !follower.getFollowing().contains(following)) {
                follower.getFollowing().add(following);
                following.getFollowers().add(follower);
                userDAO.update(follower);
                userDAO.update(following);
                return true;
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }
}
