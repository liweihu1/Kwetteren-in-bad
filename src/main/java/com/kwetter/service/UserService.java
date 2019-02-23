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

    public User changeUsername(String username, String userId){
        if (userDAO.checkUsernameAvailable(username)){
            User user = userDAO.findById(UUID.fromString(userId));
            if (user != null){
                user.setUsername(username);
                userDAO.update(user);
                return user;
            }
        }
        return null;
    }

    public User deleteUserById(UUID id){
        User userToDelete = userDAO.findById(id);
        userDAO.delete(userToDelete);
        return userToDelete;
    }

    public User followUserWithId(UUID userId, UUID followId){
        User curUser = userDAO.findById(userId);
        User userToFollow = userDAO.findById(followId);
        followUser(curUser, userToFollow);
        return curUser;
    }

    public User followUserWithUsername(UUID userId, String username){
        User curUser = userDAO.findById(userId);
        User followUser = userDAO.findByUsername(username);
        followUser(curUser, followUser);
        return curUser;
    }

    public boolean unFollowInfoUserWithUsername(UUID userId, String username){
        User curUser = userDAO.findById(userId);
        User followUser = userDAO.findByUsername(username);
        return stopFollowingUser(curUser, followUser);
    }

    public boolean unFollowInfoUserWithId(UUID userId, UUID followId){
        User curUser = userDAO.findById(userId);
        User followUser = userDAO.findById(followId);
        return stopFollowingUser(curUser, followUser);
    }

    private boolean stopFollowingUser(User follower, User following){
        try {
            follower.getFollowing().remove(following);
            following.getFollowers().remove(follower);
            userDAO.update(follower);
            userDAO.update(following);
            return true;
        } catch (Exception e){
            return false;
        }
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
