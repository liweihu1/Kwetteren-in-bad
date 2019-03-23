package com.kwetter.controllers;

import com.kwetter.controllers.validator.RoleValidator;
import com.kwetter.domain.Role;
import com.kwetter.domain.Token;
import com.kwetter.domain.User;
import com.kwetter.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@ManagedBean(name = "adminDashboard")
@RequestScoped
@RolesAllowed("Administrator")
public class AdminDashboardController {

    @Inject
    private UserService userService;

    private User currentUser;

    public AdminDashboardController() throws IOException {
        currentUser = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        RoleValidator.validateUserRole(currentUser, Role.Administrator);
    }

    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    public String selectUser(){
        return "kweet_dashboard";
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
