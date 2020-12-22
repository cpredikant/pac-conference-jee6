package de.predikant.conference.web.rest.api.unsecure;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/private/talk")
public interface TalkUnsecureRestService {

    @GET
    Response listAllTalks();

    @GET
    @Path("/{id:[0-9][0-9]*}")
    Response listTalkById(@PathParam("id") String id);

    @GET
    @Path("/{name}")
    Response listTalkByName(@PathParam("name") String name);

}