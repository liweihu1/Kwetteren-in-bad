package com.kwetter.rest;

import com.kwetter.domain.Tweet;
import com.kwetter.domain.User;
import com.kwetter.dto.TweetDTO;
import com.kwetter.service.TweetService;
import com.kwetter.service.UserService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<TweetDTO> getAllTweets(){
        List<Tweet> tweets = tweetService.getAllTweets();
        return convertTweetListToTweetDTOList(tweets);
    }

    @GET
    @Path("/search/{searchString}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<TweetDTO> getTweetsBySearchString(@PathParam("searchString") String search){
        List<Tweet> tweets = tweetService.getTweetsBySearchString(search);
        return convertTweetListToTweetDTOList(tweets);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TweetDTO getTweetById(@PathParam("id") UUID id){
        Tweet tweet = tweetService.getTweetById(id);
        if (tweet != null){
            return new TweetDTO(tweet);
        }
        return null;
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<TweetDTO> getLatestTweetsForUser(@PathParam("userId") UUID id){
        List<Tweet> userTweets = tweetService.getTweetsForUserId(id);
        return convertTweetListToTweetDTOList(userTweets);
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

    private List<TweetDTO> convertTweetListToTweetDTOList(List<Tweet> tweets){
        if (tweets.size() > 0){
            List<TweetDTO> results = new ArrayList<>();
            for (Tweet t : tweets){
                results.add(new TweetDTO(t));
            }
            return results;
        }
        return new ArrayList<>();
    }
}
