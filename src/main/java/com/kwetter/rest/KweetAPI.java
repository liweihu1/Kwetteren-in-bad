package com.kwetter.rest;

import com.kwetter.domain.Kweet;
import com.kwetter.dto.KweetDTO;
import com.kwetter.filters.interfaces.JWTTokenNeeded;
import com.kwetter.service.KweetService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("kweet")
public class KweetAPI {
    @Inject
    private KweetService kweetService;

    public KweetAPI(){
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getAllKweets(){
        List<Kweet> Kweets = kweetService.getAllKweets();
        return Response.ok(convertKweetListToKweetDTOList(Kweets)).build();
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getLatestKweetsForUser(@PathParam("userId") UUID id){
        List<Kweet> userKweets = kweetService.getKweetsForUserId(id);
        return Response.ok(convertKweetListToKweetDTOList(userKweets)).build();
    }

    @GET
    @Path("/search/{searchString}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getKweetsBySearchString(@PathParam("searchString") String search){
        List<Kweet> Kweets = kweetService.getKweetsBySearchString(search);
        return Response.ok(convertKweetListToKweetDTOList(Kweets)).build();
    }

    @GET
    @Path("/user/follow/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @JWTTokenNeeded
    public Response getKweetsByUserIdWithFollowing(@PathParam("id") UUID id){
        List<Kweet> kweets = kweetService.getKweetsByUserIdWithFollowing(id);
        return Response.ok(convertKweetListToKweetDTOList(kweets)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response getKweetById(@PathParam("id") UUID id){
        Kweet Kweet = kweetService.getKweetById(id);
        if (Kweet != null){
            return Response.ok(new KweetDTO(Kweet)).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{authorId}/{kweetId}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response removeKweetById(@PathParam("authorId") UUID authorId, @PathParam("kweetId") UUID kweetId){
        Kweet k = kweetService.getKweetById(kweetId);
        if (k.getAuthor().getId().equals(authorId) && (k = kweetService.removeKweetById(kweetId)) != null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(500).entity(k).build();
        }
    }

    @POST
    @Path("/create/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @JWTTokenNeeded
    public Response createKweet(KweetDTO kweetDTO){
        if (kweetDTO != null){
            KweetDTO result = new KweetDTO(kweetService.createKweet(kweetDTO.getMessage(), kweetDTO.getAuthor().getId()));
            return Response.ok(result).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    private List<KweetDTO> convertKweetListToKweetDTOList(List<Kweet> kweets){
        if (kweets.size() > 0){
            List<KweetDTO> results = new ArrayList<>();
            for (Kweet t : kweets){
                results.add(new KweetDTO(t));
            }
            return results;
        }
        return new ArrayList<>();
    }
}
