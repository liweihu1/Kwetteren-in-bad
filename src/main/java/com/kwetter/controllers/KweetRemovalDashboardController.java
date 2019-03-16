package com.kwetter.controllers;

import com.kwetter.domain.Kweet;
import com.kwetter.domain.User;
import com.kwetter.service.KweetService;
import com.kwetter.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SessionScoped
@ManagedBean(name = "kweetDashboard", eager = true)
public class KweetRemovalDashboardController {
    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    private String userId;

    private FacesContext fc;

    public KweetRemovalDashboardController() {
        this.fc = FacesContext.getCurrentInstance();
        userId = fc.getExternalContext().getRequestParameterMap().get("userId");
    }

    public String removeKweet(){
        try {
            UUID id  = UUID.fromString(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("kweetId"));
            kweetService.removeKweetById(id);
            return "kweet_dashboard";
        } catch (Exception e){
            e.printStackTrace();
            return "kweet_dashboard";
        }
    }

    public List<Kweet> getKweets(){
        if (userId != null && !userId.isEmpty()){
            return kweetService.getKweetsForUserId(UUID.fromString(userId));
        }
        return new ArrayList<>();
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
