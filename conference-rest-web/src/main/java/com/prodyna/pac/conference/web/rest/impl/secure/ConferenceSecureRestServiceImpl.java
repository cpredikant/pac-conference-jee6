/**
 * 
 */
package com.prodyna.pac.conference.web.rest.impl.secure;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.InternalServerErrorException;
import org.jboss.resteasy.spi.NotFoundException;
import org.slf4j.Logger;

import com.prodyna.pac.conference.api.ConferenceService;
import com.prodyna.pac.conference.exception.ConferenceNotFoundException;
import com.prodyna.pac.conference.model.Conference;
import com.prodyna.pac.conference.web.rest.api.secure.ConferenceSecureRestService;

@Path("/private")
@RequestScoped
public class ConferenceSecureRestServiceImpl implements
		ConferenceSecureRestService {

	@Inject
	private ConferenceService conferenceService;

	@Inject
	Logger logger;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PrivateRestService#createConferences
	 * (com.prodyna.pac.conference.model.Conference)
	 */
	@POST
	@Path("/conference")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response createConferences(Conference conference) {

		try {
			conferenceService.createConference(conference);
			
		} catch (Exception e) {
			logger.error("Error create Conference " + conference, e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(conference).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PrivateRestService#updateConference
	 * (com.prodyna.pac.conference.model.Conference)
	 */
	@PUT
	@Path("/conference")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response updateConference(Conference conference) {
		Conference updateConference = null;
		try {
			updateConference = conferenceService.updateConference(conference);
		} catch (ConferenceNotFoundException e) {
			logger.error("Conference not found for update " + conference, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error updating Conference " + conference, e);
			throw new NotFoundException(e);
		}

		return Response.ok(updateConference).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.pac.conference.web.rest.api.PrivateRestService#
	 * listConferencesByName(java.lang.String)
	 */
	@Override
	@DELETE
	@Path("/conference/{id:[0-9][0-9]*}")
	public Response deleteConference(@PathParam("id") String id) {

		try {
			conferenceService.deleteConference(Long.valueOf(id));
		} catch (ConferenceNotFoundException e) {
			logger.error("Conference not found for delete " + id, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error deleting Conference " + id, e);
			throw new NotFoundException(e);
		}

		return Response.ok().build();
	}

}
