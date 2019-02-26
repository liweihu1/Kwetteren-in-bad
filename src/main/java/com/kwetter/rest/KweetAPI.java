package com.kwetter.rest;

import com.kwetter.domain.Kweet;
import com.kwetter.dto.KweetDTO;
import com.kwetter.interceptor.AuthInterceptor;
import com.kwetter.service.KweetService;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    @Path("/create/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Interceptors(AuthInterceptor.class)
    public KweetDTO createKweet(@PathParam("id") UUID userId, KweetDTO kweetDTO){
        if (kweetDTO != null){
            return new KweetDTO(kweetService.createKweet(kweetDTO.getMessage(), kweetDTO.getAuthorId()));
        }
        return null;
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
