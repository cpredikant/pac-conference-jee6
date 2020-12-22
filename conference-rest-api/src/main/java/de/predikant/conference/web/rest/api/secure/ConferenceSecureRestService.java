package de.predikant.conference.web.rest.api.secure;

import de.predikant.conference.service.model.Conference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/public/conference")
public interface ConferenceSecureRestService {

    @POST
    Response createConference(Conference conference);

    @PUT
    Response updateConference(Conference conference);

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteConference(@PathParam("id") String id);

}
