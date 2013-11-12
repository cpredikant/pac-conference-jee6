/**
 * 
 */
package com.prodyna.pac.conference.web.rest.impl.unsecure;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import com.prodyna.pac.conference.web.rest.api.unsecure.TalkUnsecureRestService;

@Path("/public")
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
	 * com.prodyna.pac.conference.web.rest.api.PublicRestService#listAllTalks()
	 */
	@GET
	@Path("/talk")
	@Produces(MediaType.APPLICATION_JSON)
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
	 * com.prodyna.pac.conference.web.rest.api.PublicRestService#listTalkById
	 * (java.lang.String)
	 */
	@GET
	@Path("/talk/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response listTalkById(@PathParam("id") String id) {
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
	 * com.prodyna.pac.conference.web.rest.api.PublicRestService#listTalkByName
	 * (java.lang.String)
	 */
	@GET
	@Path("/talk/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response listTalkByName(@PathParam("name") String name) {
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
