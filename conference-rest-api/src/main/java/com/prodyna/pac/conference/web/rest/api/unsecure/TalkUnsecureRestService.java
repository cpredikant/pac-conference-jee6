package com.prodyna.pac.conference.web.rest.api.unsecure;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface TalkUnsecureRestService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAllTalks();

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response listTalkById(@PathParam("id") String id);

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listTalkByName(@PathParam("name") String name);

}