package com.kwetter.controllers;

import com.kwetter.domain.Role;
import com.kwetter.domain.Token;
import com.kwetter.dto.UserDTO;
import com.kwetter.service.AuthService;
import com.kwetter.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

@ManagedBean(name = "loginController")
@SessionScoped
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
            return "success";
        } else {
            username = null;
            password = null;
            context.addMessage(null, new FacesMessage("Unknown login. Please try again."));
            return "failure";
        }
    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public String getPageBasedOnRole(){
        Token token = (Token) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("token");
        if (token != null && token.getUser() != null){
            if (token.getUser().getRoles().contains(Role.Administrator)) {
                return "success";
            } else if (token.getUser().getRoles().contains(Role.Moderator)) {
                return "success";
            } else if (token.getUser().getRoles().contains(Role.Standard)){
                return "success";
            }
            return logout();
        } else {
            return logout();
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
