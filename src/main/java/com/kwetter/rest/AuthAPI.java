package com.kwetter.rest;

import com.kwetter.domain.Role;
import com.kwetter.domain.Token;
import com.kwetter.dto.JWTTokenDTO;
import com.kwetter.dto.LoginDTO;
import com.kwetter.filters.KeyGenerator;
import com.kwetter.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Path("Auth")
public class AuthAPI {
    @Context
    private UriInfo uriInfo;

    @Inject
    private AuthService authService;

    private KeyGenerator keyGenerator;

    public AuthAPI(){
        keyGenerator = new KeyGenerator();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response login(LoginDTO loginDTO){
        Token token;
        if (!loginDTO.getPassword().isEmpty() && !loginDTO.getUsername().isEmpty() && (token = authService.login(loginDTO.getUsername(), loginDTO.getPassword())) != null) {
            JWTTokenDTO jwtToken = new JWTTokenDTO(token.getUser().getUsername(), generateToken(loginDTO.getUsername(), token.getUser().getRoles()), token.getUser().getId());
            return Response.ok(jwtToken, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NO_CONTENT).entity("The user credentials were not found or incorrect.").build();
    }

    @DELETE
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response logout(LoginDTO loginDTO){
        //WRITE LOGOUT WITH JWT
        return Response.status(500).build();
    }

    private String generateToken(String username, List<Role> roles) {
        Key key = keyGenerator.generateKey();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date expirationDate = cal.getTime();
        Claims claims = Jwts.claims();
        claims.put("roles", roles);
        return Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
