package com.prodyna.pac.conference.web.rest.api.unsecure;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/public")
public interface TalkUnsecureRestService {
	
	@GET
	@Path("/talk")
	@Produces(MediaType.APPLICATION_JSON)
    public Response listAllTalks();
	
	
	@GET
	@Path("/talk/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response listTalkById(@PathParam("id") String id);
	
	@GET
	@Path("/talk/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response listTalkByName(@PathParam("name") String id);

	

}