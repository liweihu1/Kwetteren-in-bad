package com.kwetter.controllers;

import com.kwetter.domain.Kweet;
import com.kwetter.domain.User;
import com.kwetter.service.KweetService;
import com.kwetter.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
@ManagedBean(name = "KweetRemovalDashboardController")
public class KweetRemovalDashboardController {

    @ManagedProperty("#{param['id']}")
    private String userId;

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    public List<Kweet> getKweets(){
        return kweetService.getKweetsForUserId(UUID.fromString(userId));
    }

    public User getUser(){
        return userService.getUserById(UUID.fromString(userId));
    }

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
