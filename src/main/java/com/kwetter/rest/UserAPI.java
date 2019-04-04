package com.kwetter.rest;

import com.kwetter.domain.Role;
import com.kwetter.domain.User;
import com.kwetter.dto.*;
import com.kwetter.filters.interfaces.JWTTokenNeeded;
import com.kwetter.service.UserService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletResponse;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Path("user")
public class UserAPI {
    @Inject
    private UserService userService;

    public UserAPI(){
    }

    @GET
    @Path("/byId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response getUserById(@PathParam("id") String id, @Context HttpServletResponse response) {
        User user = this.userService.getUserById(UUID.fromString(id));
        if (user != null) {
            return Response.ok(new UserDTO(user)).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUsername(@PathParam("username") String username, @Context HttpServletResponse response) {
        User user = this.userService.getUserByUsername(username);
        if (user != null) {
            return Response.ok(new UserDTO(user)).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/followers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response getFollowersForUserWithId(@PathParam("id") UUID id){
        List<User> followers = userService.getFollowersForUserWithId(id);
        if (followers.size() > 0) {
            return Response.ok(convertUserListToUserDTOList(followers)).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/following/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response getFollowingForUserWithId(@PathParam("id") UUID id){
        List<User> following = userService.getFollowingForUserWithId(id);
        if (following.size() > 0) {
            return Response.ok(convertUserListToUserDTOList(following)).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(){
        List<User> users = this.userService.getAllUsers();
        if (users.size() > 0) {
            return Response.ok(convertUserListToUserDTOList(users)).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/update/username")
    @Consumes(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response updateUsername(UsernameDTO usernameInfo){
        User user;
        if ((user = this.userService.changeUsername(usernameInfo.getUsername(), usernameInfo.getUserId())) != null) {
            return Response.ok(new UserDTO(user)).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response deleteUserById(@PathParam("id") UUID id){
        User user;
        if ((user = userService.deleteUserById(id))!= null) {
            return Response.ok(new UserDTO(user)).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/add/role")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response addRoleToUser(RoleRequestDTO roleRequest){
        List<Role> roles = new ArrayList<>();
        for(String r: roleRequest.getRoles()){
            roles.add(Enum.valueOf(Role.class, r));
        }

        User user;
        if ((user = userService.addRolesToUser(roleRequest.getId(), roles)) != null) {
            return Response.ok(new UserDTO(user)).build();
        }
        return Response.status(500).build();
    }

    @POST
    @Path("/create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(CreateUserDTO user){
        if (user != null){
            User newUser = new User(UUID.randomUUID(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getBiography(), user.getWebsite(), user.getLocation(), new HashSet<>(), new HashSet<>(), new ArrayList(){{ add(Role.Standard); }}, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            return Response.ok(new UserDTO(this.userService.createUser(newUser))).build();
        }
        return Response.status(500).build();
    }

    @POST
    @Path("/follow")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response followUserWith(FollowDTO followInfo){
        if (!followInfo.getUserId().isEmpty()){
            UUID id = UUID.fromString(followInfo.getUserId());
            if (!followInfo.getUsername().isEmpty()){
                return Response.ok(new UserDTO(this.userService.followUserWithUsername(id, followInfo.getUsername()))).build();
            } else if (!followInfo.getFollowingId().isEmpty()){
                return Response.ok(new UserDTO(this.userService.followUserWithId(id, UUID.fromString(followInfo.getFollowingId())))).build();
            }
        }
        return Response.status(500).build();
    }

    @POST
    @Path("/unfollow")
    @Consumes(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response unFollowUser(FollowDTO unFollowInfo){
        if (!unFollowInfo.getUserId().isEmpty()){
            UUID id = UUID.fromString(unFollowInfo.getUserId());
            if (!unFollowInfo.getUsername().isEmpty()){
                return Response.ok(new UserDTO(this.userService.unFollowUserWithUsername(id, unFollowInfo.getUsername()))).build();
            } else if (!unFollowInfo.getFollowingId().isEmpty()){
                return Response.ok(new UserDTO(this.userService.unFollowUserWithId(id, UUID.fromString(unFollowInfo.getFollowingId())))).build();
            }
        }
        return Response.status(500).build();
    }

    private List<UserDTO> convertUserListToUserDTOList(List<User> users) {
        List<UserDTO> dtoUsers = new ArrayList<>();
        for(User u : users){
            dtoUsers.add(new UserDTO(u));
        }
        return dtoUsers;
    }
}
