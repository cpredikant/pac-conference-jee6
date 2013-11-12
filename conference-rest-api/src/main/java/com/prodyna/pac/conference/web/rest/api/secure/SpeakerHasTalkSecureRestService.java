package com.prodyna.pac.conference.web.rest.api.secure;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/private")
public interface SpeakerHasTalkSecureRestService {

	public Response assign(String speakerId, String talkId);

	public Response unassign(String speakerId, String talkId);

}
