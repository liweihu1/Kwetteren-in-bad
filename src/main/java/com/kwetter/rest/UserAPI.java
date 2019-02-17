package com.kwetter.rest;

import com.kwetter.domain.User;
import com.kwetter.dto.UserDTO;
import com.kwetter.service.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("user")
public class UserAPI {
    @Inject
    private UserService userService;

    public UserAPI(){
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUserById(@PathParam("id") UUID id, @Context HttpServletResponse response) {
        User user = this.userService.getUserById(id);
        if (user != null) {
            return new UserDTO(user);
        }
        return null;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getAllUsers(){
        List<User> users = this.userService.getAllUsers();
        if (users != null && users.size() > 0){
            List<UserDTO> dtoUsers = new ArrayList<>();
            for(User u : users){
                dtoUsers.add(new UserDTO(u));
            }
        }
        return new ArrayList<>();
    }
}
