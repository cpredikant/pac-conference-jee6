package com.prodyna.pac.conference.web.rest.api.secure;

import javax.ws.rs.core.Response;

import com.prodyna.pac.conference.model.Conference;


public interface ConferenceSecureRestService {
	
	public Response createConferences(Conference conference);
	
	public Response updateConference(Conference conference);

	public Response deleteConference(String id);

}
