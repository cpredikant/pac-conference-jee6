
package de.predikant.conference.web.rest.unsecure;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;


import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import de.predikant.conference.service.api.TalkService;
import de.predikant.conference.service.exception.TalkNotFoundException;
import de.predikant.conference.service.model.Talk;
import de.predikant.conference.web.rest.api.unsecure.TalkUnsecureRestService;

@Path("/public/talk")
@RequestScoped
public class TalkUnsecureRestServiceImpl implements TalkUnsecureRestService {

	@Inject
	private TalkService talkService;
	
	@Inject
	private Logger logger;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.predikant.conference.web.rest.api.PublicRestService#listAllTalks()
	 */
	
	@Override
	public Response listAllTalks() {
		List<Talk> talk = null;
		try {
			talk = talkService.findAll();

		} catch (Exception e) {
			logger.error("Error listing all Talks", e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(talk).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.predikant.conference.web.rest.api.PublicRestService#listTalkById
	 * (java.lang.String)
	 */
	
	@Override
	public Response listTalkById(String id) {
		Talk talk = null;
		try {
			talk = talkService.findTalkById(Long.valueOf(id));
		} catch (TalkNotFoundException e) {
			logger.error("Talk not Found by id " + id, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error find Talk by id " + id, e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(talk).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.predikant.conference.web.rest.api.PublicRestService#listTalkByName
	 * (java.lang.String)
	 */
	
	@Override
	public Response listTalkByName(String name) {
		List<Talk> talks = null;
		try {
			talks = talkService.findTalksByName(name);

		} catch (Exception e) {
			logger.error("Error finding Talks by name " + name, e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(talks).build();
	}

}
