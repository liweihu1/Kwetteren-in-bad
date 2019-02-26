package com.kwetter.rest;

import com.kwetter.domain.Token;
import com.kwetter.dto.TokenDTO;
import com.kwetter.dto.UserDTO;
import com.kwetter.service.AuthService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
