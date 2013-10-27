package com.prodyna.pac.conference.web.rest;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.prodyna.pac.conference.client.api.ConferenceService;
import com.prodyna.pac.conference.client.model.Conference;

@Path("/conferences")
@RequestScoped
public class ConferenceRESTService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConferenceService conferenceService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createConference(Conference conference) {

		conferenceService.createConference(conference);

		return Response.ok(conference).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateConference(Conference conference) {

		conferenceService.updateConference(conference);

		return Response.ok(conference).build();

	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteConference(Conference conference) {

		conferenceService.deleteConference(conference);

		return Response.ok().build();

	}

	@GET
	@Path("/byId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findConferenceById(@PathParam("id") long id) {

		Conference conference = conferenceService.findConferenceById(id);

		Response response;

		if (conference != null) {
			response = Response.ok().entity(conference).build();
		} else {
			response = Response.noContent().build();
		}

		return response;

	}

	@GET
	@Path("/byName/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listConferencesByName(@PathParam("name") String name) {

		List<Conference> conferences = conferenceService
				.findConferenceByName(name);

		Response response;

		if (conferences != null && conferences.size() > 0) {
			response = Response.ok().entity(conferences).build();
		} else {
			response = Response.noContent().build();
		}

		return response;

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {

		List<Conference> conferences = conferenceService.findAll();
		
		Response response;

		if (conferences != null && conferences.size() > 0) {
			response = Response.ok().entity(conferences).build();
		} else {
			response = Response.noContent().build();
		}

		return response;

	}

}
