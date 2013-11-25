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

import com.prodyna.pac.conference.service.api.SpeakerHasTalkService;
import com.prodyna.pac.conference.service.api.SpeakerService;
import com.prodyna.pac.conference.service.api.TalkService;
import com.prodyna.pac.conference.service.exception.SpeakerNotFoundException;
import com.prodyna.pac.conference.service.exception.TalkNotFoundException;
import com.prodyna.pac.conference.service.model.Speaker;
import com.prodyna.pac.conference.service.model.Talk;
import com.prodyna.pac.conference.web.rest.api.secure.SpeakerHasTalkSecureRestService;

@Path("/private/speaker")
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

	@Override
	public Response assign(String speakerId, String talkId) {
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

	@Override
	public Response unassign(String speakerId, String talkId) {
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
