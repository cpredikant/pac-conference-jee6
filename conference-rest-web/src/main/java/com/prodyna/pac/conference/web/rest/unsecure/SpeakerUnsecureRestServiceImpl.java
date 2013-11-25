/**
 * 
 */
package com.prodyna.pac.conference.web.rest.unsecure;

import java.util.List;

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
import com.prodyna.pac.conference.web.rest.api.unsecure.SpeakerUnsecureRestService;

@Path("/public/speaker")
@RequestScoped
public class SpeakerUnsecureRestServiceImpl implements SpeakerUnsecureRestService {

	@Inject
	private SpeakerService speakerService;
	
	@Inject
	private Logger logger;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PublicRestService#listAllTalks()
	 */
	
	@Override
	public Response listAllSpeakers() {
		List<Speaker> speakers = null;
		try {
			speakers = speakerService.findAll();

		} catch (Exception e) {
			logger.error("Error listing all Speaker", e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(speakers).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PublicRestService#listTalkById
	 * (java.lang.String)
	 */
	
	@Override
	public Response listSpeakerById( String id) {
		Speaker speaker = null;
		try {
			speaker = speakerService.findSpeakerById(Long.valueOf(id));
		} catch (SpeakerNotFoundException e) {
			logger.error("Speaker not Found by id " + id, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error find Speaker by id " + id, e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(speaker).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PublicRestService#listTalkByName
	 * (java.lang.String)
	 */
	
	@Override
	public Response listSpeakersByName(String name) {
		List<Speaker> speakers = null;
		try {
			speakers = speakerService.findSpeakersByName(name);

		} catch (Exception e) {
			logger.error("Error finding Speakers by name " + name, e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(speakers).build();
	}

}
