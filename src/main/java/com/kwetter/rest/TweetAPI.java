package com.kwetter.rest;

import com.kwetter.domain.Tweet;
import com.kwetter.domain.User;
import com.kwetter.dto.TweetDTO;
import com.kwetter.service.TweetService;
import com.kwetter.service.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Path("tweet")
public class TweetAPI {
    @Inject
    private TweetService tweetService;

    @Inject
    private UserService userService;

    public TweetAPI(){
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TweetDTO getTweetById(@PathParam("id") UUID id, @Context HttpServletResponse response){
        Tweet tweet = tweetService.getTweetById(id);
        if (tweet != null){
            return new TweetDTO(tweet);
        }
        return null;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createTweet(TweetDTO tweetDTO){
        if (tweetDTO != null){
            User tweetUser = userService.getUserById(UUID.fromString(tweetDTO.getAuthorId()));
            Tweet tweet = new Tweet(UUID.randomUUID(), tweetUser, tweetDTO.getMessage(), new Date(), new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            return tweetService.createTweet(tweet);
        }
        return false;
    }

}
