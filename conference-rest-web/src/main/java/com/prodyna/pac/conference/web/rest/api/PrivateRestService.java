package com.prodyna.pac.conference.web.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.prodyna.pac.conference.model.Conference;
import com.prodyna.pac.conference.model.Talk;

//TODO: Add privacy. See Chapter 35. Securing JAX-RS and RESTeasy 
@Path("/private")
public interface PrivateRestService {

	@POST
	@Path("/conference")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createConferences(Conference conference);

	@PUT
	@Path("/conference")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateConference(Conference conference);

	@DELETE
	@Path("/conference/{id:[0-9][0-9]*}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listConferencesByName(@PathParam("id") String id);

	@POST
	@Path("/talk")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTalk(Talk talk);

	@PUT
	@Path("/talk")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTalk(Talk talk);

	@DELETE
	@Path("/talk/{id:[0-9][0-9]*}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteTalk(@PathParam("id") String id);

}
