/**
 * 
 */
package com.prodyna.pac.conference.web.rest.secure;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import com.prodyna.pac.conference.service.api.TalkService;
import com.prodyna.pac.conference.service.exception.TalkNotFoundException;
import com.prodyna.pac.conference.service.model.Talk;
import com.prodyna.pac.conference.web.rest.api.secure.TalkSecureRestService;

@Path("/private/talk")
@RequestScoped
public class TalkSecureRestServiceImpl implements TalkSecureRestService {
	
	@Inject
	private Logger logger;
	
	@Inject
	private TalkService talkService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PrivateRestService#createTalk
	 * (com.prodyna.pac.conference.model.Talk)
	 */
	@Override
	public Response createTalk(Talk talk) {
		try {
			talkService.createTalk(talk);
			
		} catch (Exception e) {
			logger.error("Error create Talk " + talk, e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(talk).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PrivateRestService#updateTalk
	 * (com.prodyna.pac.conference.model.Talk)
	 */
	@Override
	public Response updateTalk(Talk talk) {
		Talk updatedTalk = null;
		try {
			updatedTalk = talkService.updateTalk(talk);
			
		} catch (TalkNotFoundException e) {
			logger.error("Talk not found for update " + talk, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error updating Talk " + talk, e);
			throw new NotFoundException(e);
		}

		return Response.ok(updatedTalk).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PrivateRestService#deleteTalk
	 * (java.lang.String)
	 */
	
	@Override
	public Response deleteTalk(String id) {
		try {
			talkService.deleteTalk(Long.valueOf(id));
		} catch (TalkNotFoundException e) {
			logger.error("Talk not found for delete " + id, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error deleting Talk " + id, e);
			throw new NotFoundException(e);
		}

		return Response.ok().build();
	}

}
