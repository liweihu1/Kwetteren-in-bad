package com.kwetter.controllers;

import com.kwetter.domain.User;
import com.kwetter.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

@ManagedBean(name = "adminDashboard")
@RequestScoped
@RolesAllowed("Administrator")
public class AdminDashboardController {

    @Inject
    private UserService userService;

    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    public String selectUser(){
        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId"));
        return "kweet_dashboard";
    }
}
