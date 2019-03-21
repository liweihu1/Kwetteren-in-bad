package com.kwetter.controllers;

import com.kwetter.callback.LoginCallbackHandler;
import com.kwetter.domain.Role;
import com.kwetter.domain.Token;
import com.kwetter.service.AuthService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.IOException;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {

    private String username;
    private String password;

    @Inject
    private AuthService authService;

    private LoginContext lc;

    public LoginController(){
    }

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            LoginCallbackHandler handler = new LoginCallbackHandler();
            handler.setPassword(password);
            handler.setUsername(username);
            lc = new LoginContext("kwetter-security-api", handler);
            lc.login();
            context.getExternalContext().redirect(context.getExternalContext().getApplicationContextPath() + "/view//admin/dashboard.xhtml");
            Token token = authService.login(username, password);
            context.getExternalContext().getSessionMap().put("token", token);
        } catch (Exception e){
                username = null;
                password = null;
                context.addMessage(null, new FacesMessage("Unknown login. Please try again."));
                context.getExternalContext().redirect("failure.xhtml");
            e.printStackTrace();
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
                return "/admin-dashboard";
            } else if (token.getUser().getRoles().contains(Role.Moderator)) {
                return "admin-dashboard";
            } else if (token.getUser().getRoles().contains(Role.Standard)){
                return "/admin-dashboard/";
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
