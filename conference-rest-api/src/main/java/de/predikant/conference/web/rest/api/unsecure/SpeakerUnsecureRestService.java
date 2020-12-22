package de.predikant.conference.web.rest.api.unsecure;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/private/speaker")
public interface SpeakerUnsecureRestService {

    @GET
    Response listAllSpeakers();

    @GET
    @Path("/{id:[0-9][0-9]*}")
    Response listSpeakerById(@PathParam("id") String id);

    @GET
    @Path("/{name}")
    Response listSpeakersByName(@PathParam("name") String name);

}