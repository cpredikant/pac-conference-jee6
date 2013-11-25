/**
 * 
 */
package com.prodyna.pac.conference.web.rest.secure;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.InternalServerErrorException;
import org.jboss.resteasy.spi.NotFoundException;
import org.slf4j.Logger;

import com.prodyna.pac.conference.service.api.SpeakerService;
import com.prodyna.pac.conference.service.exception.SpeakerNotFoundException;
import com.prodyna.pac.conference.service.model.Speaker;
import com.prodyna.pac.conference.web.rest.api.secure.SpeakerSecureRestService;

@Path("/private/speaker")
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

	@Override
	public Response deleteSpeaker(String id) {
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
