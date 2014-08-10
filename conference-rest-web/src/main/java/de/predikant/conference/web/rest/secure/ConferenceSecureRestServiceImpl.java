/**
 * 
 */
package de.predikant.conference.web.rest.secure;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import de.predikant.conference.service.api.ConferenceService;
import de.predikant.conference.service.exception.ConferenceNotFoundException;
import de.predikant.conference.service.model.Conference;
import de.predikant.conference.web.rest.api.secure.ConferenceSecureRestService;

@Path("/private/conference")
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
	 * de.predikant.conference.web.rest.api.PrivateRestService#createConferences
	 * (de.predikant.conference.model.Conference)
	 */
	@Override
	public Response createConference(Conference conference) {

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
	 * de.predikant.conference.web.rest.api.PrivateRestService#updateConference
	 * (de.predikant.conference.model.Conference)
	 */
	
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
	 * @see de.predikant.conference.web.rest.api.PrivateRestService#
	 * listConferencesByName(java.lang.String)
	 */
	@Override
	public Response deleteConference(String id) {

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
