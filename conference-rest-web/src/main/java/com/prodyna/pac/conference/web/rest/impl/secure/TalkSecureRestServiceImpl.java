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

import com.prodyna.pac.conference.api.TalkService;
import com.prodyna.pac.conference.exception.TalkNotFoundException;
import com.prodyna.pac.conference.model.Talk;
import com.prodyna.pac.conference.web.rest.api.secure.TalkSecureRestService;

@Path("/private")
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
	@POST
	@Path("/talk")
	@Produces(MediaType.APPLICATION_JSON)
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
	@PUT
	@Path("/talk")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
	@DELETE
	@Path("/talk/{id:[0-9][0-9]*}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response deleteTalk(@PathParam("id") String id) {
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
