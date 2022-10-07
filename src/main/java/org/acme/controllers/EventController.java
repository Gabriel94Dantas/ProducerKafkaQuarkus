package org.acme.controllers;

import org.acme.services.EventService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/event")
public class EventController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postEvent(String event, @Context UriInfo uriInfo){
        try {
            EventService eventService = new EventService();
            if(eventService.payloadTooLarge(event)){
                return Response.status(Response.Status.REQUEST_ENTITY_TOO_LARGE).build();
            }
            eventService.sendEvent(event);

            return Response.status(Response.Status.CREATED).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
