/**
 * 
 */
package com.prodyna.pac.conference.web.rest.impl.secure;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.InternalServerErrorException;
import org.jboss.resteasy.spi.NotFoundException;
import org.slf4j.Logger;

import com.prodyna.pac.conference.api.SpeakerHasTalkService;
import com.prodyna.pac.conference.api.SpeakerService;
import com.prodyna.pac.conference.api.TalkService;
import com.prodyna.pac.conference.exception.SpeakerNotFoundException;
import com.prodyna.pac.conference.exception.TalkNotFoundException;
import com.prodyna.pac.conference.model.Speaker;
import com.prodyna.pac.conference.model.Talk;
import com.prodyna.pac.conference.web.rest.api.secure.SpeakerHasTalkSecureRestService;

@Path("/private")
@RequestScoped
public class SpeakerHasTalkSecureRestServiceImpl implements
		SpeakerHasTalkSecureRestService {

	@Inject
	private SpeakerHasTalkService speakerHasTalkService;

	@Inject
	private TalkService talkService;

	@Inject
	private SpeakerService speakerService;

	@Inject
	private Logger logger;

	@POST
	@Path("/speaker/{speakerId:[0-9][0-9]*}/assign/{talkId:[0-9][0-9]*}")
	@Override
	public Response assign(@PathParam("speakerId") String speakerId,
			@PathParam("talkId") String talkId) {
		try {
			Talk talk = talkService.findTalkById(Long.valueOf(talkId));
			Speaker speaker = speakerService.findSpeakerById(Long
					.valueOf(speakerId));
			speakerHasTalkService.assign(speaker, talk);
		} catch (TalkNotFoundException e) {
			logger.error("Talk not Found by id " + talkId, e);
			throw new NotFoundException(e);
		} catch (SpeakerNotFoundException e) {
			logger.error("Speaker not Found by id " + speakerId, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error assign Speaker and Talk", e);
			throw new InternalServerErrorException(e);
		}

		return Response.ok().build();
	}

	@DELETE
	@Path("/speaker/{speakerId:[0-9][0-9]*}/unassign/{talkId:[0-9][0-9]*}")
	@Override
	public Response unassign(@PathParam("speakerId") String speakerId,
			@PathParam("talkId") String talkId) {
		try {
			Talk talk = talkService.findTalkById(Long.valueOf(talkId));
			Speaker speaker = speakerService.findSpeakerById(Long
					.valueOf(speakerId));
			speakerHasTalkService.unassign(speaker, talk);
		} catch (TalkNotFoundException e) {
			logger.error("Talk not Found by id " + talkId, e);
			throw new NotFoundException(e);
		} catch (SpeakerNotFoundException e) {
			logger.error("Speaker not Found by id " + speakerId, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error unassign Speaker and Talk", e);
			throw new InternalServerErrorException(e);

		}

		return Response.ok().build();
	}

}
