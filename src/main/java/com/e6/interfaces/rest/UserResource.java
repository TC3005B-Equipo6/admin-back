package com.e6.interfaces.rest;

import com.e6.application.constant.UserConstants;
import com.e6.application.dto.RegisterUserDTO;
import com.e6.application.usecase.user.DeleteUserUseCase;
import com.e6.application.usecase.user.GetUserByIdUseCase;
import com.e6.application.usecase.user.GetUsersByRoleUseCase;
import com.e6.application.usecase.user.RegisterUserUseCase;
import com.e6.domain.exception.UserAlreadyExistsException;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final RegisterUserUseCase registerUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUsersByRoleUseCase getUsersByRoleUseCase;

    @Inject
    public UserResource(RegisterUserUseCase registerUserUseCase, GetUserByIdUseCase getUserByIdUseCase, DeleteUserUseCase deleteUserUseCase, GetUsersByRoleUseCase getUsersByRoleUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.getUsersByRoleUseCase = getUsersByRoleUseCase;
    }

    @POST
    public Response registerUser(@Valid RegisterUserDTO registerUserDto) {
        try {
            return Response.ok(registerUserUseCase.execute(registerUserDto)).build();
        } catch (UserAlreadyExistsException e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID id) {
        try{
            return Response.ok(getUserByIdUseCase.execute(id)).build();
        } catch (Exception e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response getUsers(){
        try {
            return Response.ok(getUsersByRoleUseCase.execute(UserConstants.ROLE_USER_ID)).build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/admin")
    public Response GetAdmins(){
        try {
            return Response.ok(getUsersByRoleUseCase.execute(UserConstants.ROLE_ADMIN_ID)).build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") UUID id){
        try {
            deleteUserUseCase.execute(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }
}
