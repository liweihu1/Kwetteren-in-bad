package com.kwetter.controllers;

import com.kwetter.domain.Role;
import com.kwetter.domain.Token;
import com.kwetter.domain.User;
import com.kwetter.service.AuthService;
import com.kwetter.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginController {

    private String username;
    private String password;

    @Inject
    private AuthService authService;

    @Inject
    private UserService userService;

    public String login(){
        Token token = authService.login(username, password);
        FacesContext context = FacesContext.getCurrentInstance();
        if (token.getUser() != null){
            context.getExternalContext().getSessionMap().put("token", token);
            return "index?faces-redirect=true";
        } else {
            username = null;
            password = null;
            context.addMessage(null, new FacesMessage("Unknown login. Please try again."));
            return null;
        }
    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "";
    }

    public String getPageBasedOnRole(){
        Token token = (Token) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("token");
        if (token != null && token.getUser() != null){
            if (token.getUser().getRoles().contains(Role.Administrator)) {
                return "";
            } else if (token.getUser().getRoles().contains(Role.Moderator)) {
                return "";
            } else if (token.getUser().getRoles().contains(Role.Standard)){
                return "";
            }
            return logout();
        } else {
            return logout();
        }
    }
}
