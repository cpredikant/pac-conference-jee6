package com.prodyna.pac.conference.web.rest;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.prodyna.pac.conference.api.ConferenceService;
import com.prodyna.pac.conference.model.Conference;

@Path("/conferences")
@RequestScoped
public class ConferenceRESTServiceImpl implements Serializable,
		ConferenceRESTService {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConferenceService conferenceService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.ConferenceRESTServicee#createConference
	 * (com.prodyna.pac.conference.client.model.Conference)
	 */
	@Override
	public Response createConference(Conference conference) {

		conferenceService.createConference(conference);
		

		return Response.ok(conference).build();
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.prodyna.pac.conference.web.rest.ConferenceRESTServicee#updateConference
//	 * (com.prodyna.pac.conference.client.model.Conference)
//	 */
//	@Override
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response updateConference(Conference conference) {
//
//		conferenceService.updateConference(conference);
//
//		return Response.ok(conference).build();
//
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.prodyna.pac.conference.web.rest.ConferenceRESTServicee#deleteConference
//	 * (com.prodyna.pac.conference.client.model.Conference)
//	 */
//	@Override
//	@DELETE
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response deleteConference(Conference conference) {
//
//		conferenceService.deleteConference(conference);
//
//		return Response.ok().build();
//
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.prodyna.pac.conference.web.rest.ConferenceRESTServicee#findConferenceById
//	 * (long)
//	 */
//	@Override
//	@Path("/byId/{id}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response findConferenceById(@PathParam("id") long id) {
//
//		Conference conference = conferenceService.findConferenceById(id);
//
//		Response response;
//
//		if (conference != null) {
//			response = Response.ok().entity(conference).build();
//		} else {
//			response = Response.noContent().build();
//		}
//
//		return response;
//
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.prodyna.pac.conference.web.rest.ConferenceRESTServicee#
//	 * listConferencesByName(java.lang.String)
//	 */
//	@Override
//	@Path("/byName/{name}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response listConferencesByName(@PathParam("name") String name) {
//
//		List<Conference> conferences = conferenceService
//				.findConferenceByName(name);
//
//		Response response;
//
//		if (conferences != null && conferences.size() > 0) {
//			response = Response.ok().entity(conferences).build();
//		} else {
//			response = Response.noContent().build();
//		}
//
//		return response;
//
//	}
//
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.pac.conference.web.rest.ConferenceRESTServicee#listAll()
	 */
	@Override
	
	public Response listAll() {

		return Response.ok().build();

	}

}
