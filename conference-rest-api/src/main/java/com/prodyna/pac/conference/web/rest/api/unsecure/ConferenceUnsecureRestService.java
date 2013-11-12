package com.prodyna.pac.conference.web.rest.api.unsecure;

import javax.ws.rs.core.Response;

public interface ConferenceUnsecureRestService {

	public Response listAllConferences();

	public Response listConferenceById(String id);

	public Response listConferencesByName(String name);

}