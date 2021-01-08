package de.predikant.conference.web.rest.api.secure;

import de.predikant.conference.service.model.Speaker;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/private/speaker")
public interface SpeakerSecureRestService {

    @POST
    Response createSpeaker(Speaker speaker);

    @PUT
    Response updateSpeaker(Speaker speaker);

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    Response deleteSpeaker(@PathParam("id") String id);

}
