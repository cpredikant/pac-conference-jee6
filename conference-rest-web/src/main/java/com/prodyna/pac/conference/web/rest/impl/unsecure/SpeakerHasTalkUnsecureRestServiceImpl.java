/**
 * 
 */
package com.prodyna.pac.conference.web.rest.impl.unsecure;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.InternalServerErrorException;
import org.jboss.resteasy.spi.NotFoundException;
import org.slf4j.Logger;

import com.prodyna.pac.conference.api.SpeakerHasTalkService;
import com.prodyna.pac.conference.api.SpeakerService;
import com.prodyna.pac.conference.api.TalkService;
import com.prodyna.pac.conference.exception.SpeakerHasTalkNotFoundException;
import com.prodyna.pac.conference.exception.SpeakerNotFoundException;
import com.prodyna.pac.conference.exception.TalkNotFoundException;
import com.prodyna.pac.conference.model.Speaker;
import com.prodyna.pac.conference.model.SpeakerHasTalk;
import com.prodyna.pac.conference.model.Talk;
import com.prodyna.pac.conference.web.rest.api.unsecure.SpeakerHasTalkUnsecureRestService;

@Path("/public")
@RequestScoped
public class SpeakerHasTalkUnsecureRestServiceImpl implements
		SpeakerHasTalkUnsecureRestService {

	@Inject
	private SpeakerHasTalkService speakerHasTalkService;

	@Inject
	private TalkService talkService;

	@Inject
	private SpeakerService speakerService;

	@Inject
	private Logger logger;

	@GET
	@Path("/talksBySpeaker/{speakerId:[0-9][0-9]*}/{talkId:[0-9][0-9]*}")
	@Override
	public Response findSpeakerHasTalkBySpeakerAndTalk(
			@PathParam("speakerId") String speakerId,
			@PathParam("talkId") String talkId) {
		SpeakerHasTalk speakerHasTalk = null;
		try {
			Talk talk = talkService.findTalkById(Long.valueOf(talkId));
			Speaker speaker = speakerService.findSpeakerById(Long
					.valueOf(speakerId));
			speakerHasTalk = speakerHasTalkService
					.findSpeakerHasTalkBySpeakerAndTalk(speaker, talk);
		} catch (SpeakerHasTalkNotFoundException e) {
			logger.error("SpeakerHasTalk not found", e);
			throw new NotFoundException(e);
		} catch (TalkNotFoundException e) {
			logger.error("Talk not Found by id " + talkId, e);
			throw new NotFoundException(e);
		} catch (SpeakerNotFoundException e) {
			logger.error("Speaker not Found by id " + speakerId, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error list Speakers by Talk", e);
			throw new InternalServerErrorException(e);

		}
		
		return Response.ok(speakerHasTalk).build();
	
	}

	@GET
	@Path("/speakersByTalk/{talkId:[0-9][0-9]*}")
	@Override
	public Response listSpeakersByTalk(@PathParam("talkId") String talkId) {
		List<Speaker> speakers = null;
		try {
			Talk talk = talkService.findTalkById(Long.valueOf(talkId));
			speakers = speakerHasTalkService.findSpeakersByTalk(talk);

		} catch (TalkNotFoundException e) {
			logger.error("Talk not Found by id " + talkId, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error list Speakers by Talk", e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(speakers).build();
	}

	@GET
	@Path("/talsBySpeaker/{speakerId:[0-9][0-9]*}")
	@Override
	public Response listTalksBySpeaker(@PathParam("speakerId") String speakerId) {
		List<Talk> talks = null;
		try {
			Speaker speaker = speakerService.findSpeakerById(Long
					.valueOf(speakerId));
			talks = speakerHasTalkService.findTalksBySpeaker(speaker);

		} catch (SpeakerNotFoundException e) {
			logger.error("Speaker not Found by id " + speakerId, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error list Talks by Speaker", e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(talks).build();
	}

}
