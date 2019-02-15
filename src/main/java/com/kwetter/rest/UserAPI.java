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
import java.util.UUID;

@Path("user")
public class UserAPI {
    @Inject
    UserService userService;

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
}
