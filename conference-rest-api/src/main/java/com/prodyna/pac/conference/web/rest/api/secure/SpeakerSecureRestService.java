package com.prodyna.pac.conference.web.rest.api.secure;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.prodyna.pac.conference.model.Speaker;

@Path("/private")
public interface SpeakerSecureRestService {

	public Response createSpeaker(Speaker speaker);

	public Response updateSpeaker(Speaker speaker);

	public Response deleteSpeaker(String id);

}
