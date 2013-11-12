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

import com.prodyna.pac.conference.api.SpeakerService;
import com.prodyna.pac.conference.exception.SpeakerNotFoundException;
import com.prodyna.pac.conference.model.Speaker;
import com.prodyna.pac.conference.web.rest.api.secure.SpeakerSecureRestService;

@Path("/private")
@RequestScoped
public class SpeakerSecureRestServiceImpl implements SpeakerSecureRestService {
	
	@Inject
	private Logger logger;
	
	@Inject
	private SpeakerService speakerService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PrivateRestService#createTalk
	 * (com.prodyna.pac.conference.model.Talk)
	 */
	@POST
	@Path("/speaker")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response createSpeaker(Speaker speaker) {
		try {
			speakerService.createSpeaker(speaker);
			
		} catch (Exception e) {
			logger.error("Error create Speaker " + speaker, e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(speaker).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PrivateRestService#updateTalk
	 * (com.prodyna.pac.conference.model.Talk)
	 */
	@PUT
	@Path("/speaker")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response updateSpeaker(Speaker speaker) {
		Speaker updatedSpeaker = null;
		try {
			updatedSpeaker = speakerService.updateSpeaker(speaker);
			
		} catch (SpeakerNotFoundException e) {
			logger.error("Speaker not found for update " + speaker, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error updating Speaker " + speaker, e);
			throw new NotFoundException(e);
		}

		return Response.ok(updatedSpeaker).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PrivateRestService#deleteTalk
	 * (java.lang.String)
	 */
	@DELETE
	@Path("/speaker/{id:[0-9][0-9]*}")
	@Override
	public Response deleteSpeaker(@PathParam("id") String id) {
		try {
			speakerService.deleteSpeaker(Long.valueOf(id));
		} catch (SpeakerNotFoundException e) {
			logger.error("Speaker not found for delete " + id, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error deleting Speaker " + id, e);
			throw new NotFoundException(e);
		}

		return Response.ok().build();
	}

}
