package de.predikant.conference.web.rest.api.unsecure;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/public/speaker")
public interface SpeakerHasTalkUnsecureRestService {

    @GET
    @Path("/{speakerId:[0-9][0-9]*}/{talkId:[0-9][0-9]*}")
    Response findSpeakerHasTalkBySpeakerAndTalk(
            @PathParam("speakerId") String speakerId,
            @PathParam("talkId") String talkId);

    @GET
    @Path("/SpeakerByTalk/{talkId:[0-9][0-9]*}")
    Response listSpeakersByTalk(@PathParam("talkId") String talkId);

    @GET
    @Path("/talksBySpeaker/{speakerId:[0-9][0-9]*}")
    Response listTalksBySpeaker(@PathParam("speakerId") String speakerId);

}