package com.kwetter.rest;

import com.kwetter.domain.Kweet;
import com.kwetter.domain.User;
import com.kwetter.dto.KweetDTO;
import com.kwetter.service.KweetService;
import com.kwetter.service.UserService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Path("kweet")
public class KweetAPI {
    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    public KweetAPI(){
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<KweetDTO> getAllKweets(){
        List<Kweet> Kweets = kweetService.getAllKweets();
        return convertKweetListToKweetDTOList(Kweets);
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<KweetDTO> getLatestKweetsForUser(@PathParam("userId") UUID id){
        List<Kweet> userKweets = kweetService.getKweetsForUserId(id);
        return convertKweetListToKweetDTOList(userKweets);
    }

    @GET
    @Path("/search/{searchString}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<KweetDTO> getKweetsBySearchString(@PathParam("searchString") String search){
        List<Kweet> Kweets = kweetService.getKweetsBySearchString(search);
        return convertKweetListToKweetDTOList(Kweets);
    }

    @GET
    @Path("/user/follow/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<KweetDTO> getKweetsByUserIdWithFollowing(@PathParam("id") UUID id){
        List<Kweet> kweets = kweetService.getKweetsByUserIdWithFollowing(id);
        return convertKweetListToKweetDTOList(kweets);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public KweetDTO getKweetById(@PathParam("id") UUID id){
        Kweet Kweet = kweetService.getKweetById(id);
        if (Kweet != null){
            return new KweetDTO(Kweet);
        }
        return null;
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public KweetDTO removeKweetById(@PathParam("id") UUID id){
        return new KweetDTO(kweetService.removeKweetById(id));
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public KweetDTO createKweet(KweetDTO KweetDTO){
        if (KweetDTO != null){
            User KweetUser = userService.getUserById(UUID.fromString(KweetDTO.getAuthorId()));
            Kweet Kweet = new Kweet(UUID.randomUUID(), KweetUser, KweetDTO.getMessage(), new Date(), new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            return new KweetDTO(kweetService.createKweet(Kweet));
        }
        return null;
    }

    private List<KweetDTO> convertKweetListToKweetDTOList(List<Kweet> Kweets){
        if (Kweets.size() > 0){
            List<KweetDTO> results = new ArrayList<>();
            for (Kweet t : Kweets){
                results.add(new KweetDTO(t));
            }
            return results;
        }
        return new ArrayList<>();
    }
}
