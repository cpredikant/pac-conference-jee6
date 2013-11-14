/**
 * 
 */
package com.prodyna.pac.conference.web.rest.impl.unsecure;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.InternalServerErrorException;
import org.jboss.resteasy.spi.NotFoundException;
import org.slf4j.Logger;

import com.prodyna.pac.conference.service.api.ConferenceService;
import com.prodyna.pac.conference.service.exception.ConferenceNotFoundException;
import com.prodyna.pac.conference.service.model.Conference;
import com.prodyna.pac.conference.web.rest.api.unsecure.ConferenceUnsecureRestService;

@Path("/public/conference")
@RequestScoped
public class ConferenceUnsecureRestServiceImpl implements
		ConferenceUnsecureRestService {

	@Inject
	private ConferenceService conferenceService;
	
	@Inject
	private Logger logger;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PublicRestService#listAllConferences
	 * ()
	 */
	@Override
	public Response listAllConferences() {
		List<Conference> conferences = null;
		try {
			conferences = conferenceService.findAll();

		} catch (Exception e) {
			logger.error("Error listing all Conferences", e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(conferences).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PublicRestService#listConferenceById
	 * (java.lang.String)
	 */
	
	@Override
	public Response listConferenceById(String id) {
		Conference conference = null;
		try {
			conference = conferenceService.findConferenceById(Long.valueOf(id));
		} catch (ConferenceNotFoundException e) {
			logger.error("Conference not Found by id " + id, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error find Conferences by id " + id, e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(conference).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.pac.conference.web.rest.api.PublicRestService#
	 * listConferencesByName(java.lang.String)
	 */

	@Override
	public Response listConferencesByName(String name) {
		List<Conference> conferences = null;
		try {
			conferences = conferenceService.findConferenceByName(name);

		} catch (Exception e) {
			logger.error("Error find Conferences by name " + name, e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(conferences).build();
	}

}
