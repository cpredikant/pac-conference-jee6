package com.prodyna.pac.conference.web.rest.api.unsecure;

import javax.ws.rs.core.Response;

public interface TalkUnsecureRestService {

	public Response listAllTalks();

	public Response listTalkById(String id);

	public Response listTalkByName(String name);

}