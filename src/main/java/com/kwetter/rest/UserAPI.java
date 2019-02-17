package com.kwetter.rest;

import com.kwetter.domain.Role;
import com.kwetter.domain.User;
import com.kwetter.dto.UserDTO;
import com.kwetter.service.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.*;
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
    public UserDTO getUserById(@PathParam("id") String id, @Context HttpServletResponse response) {
        User user = this.userService.getUserById(UUID.fromString(id));
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
            return dtoUsers;
        }
        return new ArrayList<>();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(UserDTO user){
        if (user != null){
            User newUser = new User(UUID.randomUUID(), user.getFirstName(), user.getLastName(), user.getBiography(), user.getWebsite(), user.getLocation(), new ArrayList<>(), new ArrayList<>(), new ArrayList(){{ add(Role.Standard); }}, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            this.userService.createUser(newUser);
        }
    }
}
