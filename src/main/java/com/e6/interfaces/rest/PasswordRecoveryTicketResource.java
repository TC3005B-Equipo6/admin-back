package com.e6.interfaces.rest;
import java.util.UUID;
import com.e6.application.dto.PasswordRecoveryRequestDto;
import com.e6.application.usecase.CreatePasswordRecoveryTicketUseCase;
import com.e6.application.usecase.GetPasswordRecoveryTicketsUseCase;
import com.e6.application.usecase.UpdatePasswordRecoveryTicketStatusUseCase;
import com.e6.domain.model.PasswordRecoveryTicket;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/tickets/password-recovery")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasswordRecoveryTicketResource {

    @Inject
    UpdatePasswordRecoveryTicketStatusUseCase updatePasswordRecoveryTicketStatusUseCase;

    @Inject
    CreatePasswordRecoveryTicketUseCase createPasswordRecoveryTicketUseCase;

    @Inject
    GetPasswordRecoveryTicketsUseCase getPasswordRecoveryTicketsUseCase;

    @POST
    public Response create(PasswordRecoveryRequestDto request) {
        PasswordRecoveryTicket ticket = createPasswordRecoveryTicketUseCase.execute(request);
        return Response.status(Response.Status.CREATED).entity(ticket).build();
    }

    @GET
    public Response getByStatus(@QueryParam("status") @DefaultValue("PENDING") String status) {
        List<PasswordRecoveryTicket> tickets = getPasswordRecoveryTicketsUseCase.execute(status);
        return Response.ok(tickets).build();
    }

    @PUT
    
    @Path("/{id}/approve")
    public Response approve(@PathParam("id") UUID id) throws Exception{
        return Response.ok(
            updatePasswordRecoveryTicketStatusUseCase.execute(
            id,
            "APPROVED"
        )
    ).build();

    }

    @PUT
    @Path("/{id}/reject")
    public Response reject(@PathParam("id") UUID id){
        try {
            return Response.ok(
                updatePasswordRecoveryTicketStatusUseCase.execute(
                id,
                "REJECTED"
            )
        ).build();

    } catch (Exception e) {
        e.printStackTrace();

        return Response.status(500)
                .entity("Error al rechazar ticket")
                .build();
    }
    }

}