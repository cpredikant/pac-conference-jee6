/**
 * 
 */
package com.prodyna.pac.conference.web.rest.impl.unsecure;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import com.prodyna.pac.conference.api.SpeakerHasTalkService;
import com.prodyna.pac.conference.web.rest.api.unsecure.SpeakerHasTalkUnsecureRestService;

@Path("/public")
@RequestScoped
public class SpeakerHasTalkUnsecureRestServiceImpl implements
		SpeakerHasTalkUnsecureRestService {

	@Inject
	private SpeakerHasTalkService speakerHasTalkService;

	@Inject
	private Logger logger;

	@GET
	@Path("/talksBySpeaker/{speakerId:[0-9][0-9]*}/{talkId:[0-9][0-9]*}")
	@Override
	public Response findSpeakerHasTalkBySpeakerAndTalk(@PathParam("speakerId") String speakerId,
			@PathParam("talkId") String talkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Path("/speakersByTalk/{talkId:[0-9][0-9]*}")
	@Override
	public Response listSpeakersByTalk(@PathParam("talkId") String talkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Path("/talsBySpeaker/{speakerId:[0-9][0-9]*}")
	@Override
	public Response listTalksBySpeaker(@PathParam("speakerId") String speakerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
