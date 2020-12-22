package de.predikant.conference.web.rest.api.secure;

import de.predikant.conference.service.model.Talk;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/public/talk")
public interface TalkSecureRestService {

    @POST
    Response createTalk(Talk talk);

    @PUT
    Response updateTalk(Talk talk);

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    Response deleteTalk(@PathParam("id") String id);

}
