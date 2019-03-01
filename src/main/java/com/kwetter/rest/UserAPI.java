package com.kwetter.rest;

import com.kwetter.domain.Role;
import com.kwetter.domain.User;
import com.kwetter.dto.FollowDTO;
import com.kwetter.dto.RoleRequestDTO;
import com.kwetter.dto.UserDTO;
import com.kwetter.dto.UsernameDTO;
import com.kwetter.service.UserService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletResponse;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUserById(@PathParam("id") String id, @Context HttpServletResponse response) {
        User user = this.userService.getUserById(UUID.fromString(id));
        if (user != null) {
            return new UserDTO(user);
        }
        return null;
    }

    @GET
    @Path("/followers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getFollowersForUserWithId(@PathParam("id") UUID id){
        return convertUserListToUserDTOList(userService.getFollowersForUserWithId(id));
    }

    @GET
    @Path("/following/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getFollowingForUserWithId(@PathParam("id") UUID id){
        return convertUserListToUserDTOList(userService.getFollowingForUserWithId(id));
    }

    @GET
    @Path("/")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getAllUsers(){
        return convertUserListToUserDTOList(this.userService.getAllUsers());
    }

    @PUT
    @Path("/update/username")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO updateUsername(UsernameDTO usernameInfo){
        return new UserDTO(this.userService.changeUsername(usernameInfo.getUsername(), usernameInfo.getUserId()));
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO deleteUserById(@PathParam("id") UUID id){
        return new UserDTO(userService.deleteUserById(id));
    }

    @PUT
    @Path("/add/role")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO addRoleToUser(RoleRequestDTO roleRequest){
        List<Role> roles = new ArrayList<>();
        for(String r: roleRequest.getRoles()){
            roles.add(Enum.valueOf(Role.class, r));
        }
        return new UserDTO(userService.addRolesToUser(roleRequest.getId(), roles));
    }

    @POST
    @Path("/create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO createUser(UserDTO user){
        if (user != null){
            User newUser = new User(UUID.randomUUID(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getBiography(), user.getWebsite(), user.getLocation(), new HashSet<>(), new HashSet<>(), new ArrayList(){{ add(Role.Standard); }}, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            return new UserDTO(this.userService.createUser(newUser));
        }
        return null;
    }

    @POST
    @Path("/follow")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO followUserWith(FollowDTO followInfo){
        if (!followInfo.getUserId().isEmpty()){
            UUID id = UUID.fromString(followInfo.getUserId());
            if (!followInfo.getUsername().isEmpty()){
                return new UserDTO(this.userService.followUserWithUsername(id, followInfo.getUsername()));
            } else if (!followInfo.getFollowingId().isEmpty()){
                return new UserDTO(this.userService.followUserWithId(id, UUID.fromString(followInfo.getFollowingId())));
            }
        }
        return null;
    }

    @POST
    @Path("/unfollow")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO unFollowUser(FollowDTO unFollowInfo){
        if (!unFollowInfo.getUserId().isEmpty()){
            UUID id = UUID.fromString(unFollowInfo.getUserId());
            if (!unFollowInfo.getUsername().isEmpty()){
                return new UserDTO(this.userService.unFollowUserWithUsername(id, unFollowInfo.getUsername()));
            } else if (!unFollowInfo.getFollowingId().isEmpty()){
                return new UserDTO(this.userService.unFollowUserWithId(id, UUID.fromString(unFollowInfo.getFollowingId())));
            }
        }
        return null;
    }

    private List<UserDTO> convertUserListToUserDTOList(List<User> users) {
        List<UserDTO> dtoUsers = new ArrayList<>();
        for(User u : users){
            dtoUsers.add(new UserDTO(u));
        }
        return dtoUsers;
    }
}
