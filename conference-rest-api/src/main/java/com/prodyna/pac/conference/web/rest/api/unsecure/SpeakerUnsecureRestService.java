package com.prodyna.pac.conference.web.rest.api.unsecure;

import javax.ws.rs.core.Response;

public interface SpeakerUnsecureRestService {

	public Response listAllSpeakers();

	public Response listSpeakerById(String id);

	public Response listSpeakersByName(String name);

}