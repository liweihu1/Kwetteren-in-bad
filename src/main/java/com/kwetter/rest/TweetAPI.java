package com.kwetter.rest;

import com.kwetter.domain.Tweet;
import com.kwetter.dto.TweetDTO;
import com.kwetter.service.TweetService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("tweet")
public class TweetAPI {
    @Inject
    private TweetService tweetService;

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

}
