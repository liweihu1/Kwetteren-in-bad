package com.kwetter.rest;

import com.kwetter.dto.TokenDTO;
import com.kwetter.dto.UserDTO;
import com.kwetter.service.AuthService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("Auth")
public class AuthAPI {
    @Inject
    private AuthService authService;

    public AuthAPI(){

    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public TokenDTO login(UserDTO userDTO){
        if (userDTO.getId() != null) {
            return new TokenDTO(authService.getTokenForUser(UUID.fromString(userDTO.getId())));
        }
        return null;
    }

    @DELETE
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void logout(UserDTO userDTO){
        if (userDTO.getId() != null) {
            authService.removeTokenforUser(UUID.fromString(userDTO.getId()));
        }
    }
}
