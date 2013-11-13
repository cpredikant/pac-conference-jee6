package com.prodyna.pac.conference.web.rest.api.secure;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface SpeakerHasTalkSecureRestService {

	@POST
	@Path("/{speakerId:[0-9][0-9]*}/assign/{talkId:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response assign(@PathParam("speakerId") String speakerId,
			@PathParam("talkId") String talkId);

	@DELETE
	@Path("/{speakerId:[0-9][0-9]*}/unassign/{talkId:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response unassign(@PathParam("speakerId") String speakerId,
			@PathParam("talkId") String talkId);

}
