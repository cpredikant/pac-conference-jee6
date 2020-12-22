package de.predikant.conference.web.rest.api.secure;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/public/talkSpeaker")
public interface SpeakerHasTalkSecureRestService {

    @POST
    @Path("/{speakerId:[0-9][0-9]*}/assign/{talkId:[0-9][0-9]*}")
    Response assign(@PathParam("speakerId") String speakerId,
                    @PathParam("talkId") String talkId);

    @DELETE
    @Path("/{speakerId:[0-9][0-9]*}/unassign/{talkId:[0-9][0-9]*}")
    Response unassign(@PathParam("speakerId") String speakerId,
                      @PathParam("talkId") String talkId);

}
