package com.prodyna.pac.conference.web.rest.api.secure;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.prodyna.pac.conference.model.Talk;

@Path("/private")
public interface TalkSecureRestService {

	public Response createTalk(Talk talk);

	public Response updateTalk(Talk talk);

	public Response deleteTalk(String id);

}
