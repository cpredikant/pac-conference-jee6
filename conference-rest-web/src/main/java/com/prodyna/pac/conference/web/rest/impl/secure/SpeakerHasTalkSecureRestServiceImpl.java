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

import org.slf4j.Logger;

import com.prodyna.pac.conference.api.SpeakerHasTalkService;
import com.prodyna.pac.conference.web.rest.api.secure.SpeakerHasTalkSecureRestService;

@Path("/private")
@RequestScoped
public class SpeakerHasTalkSecureRestServiceImpl implements SpeakerHasTalkSecureRestService {
	
	@Inject
	private Logger logger;
	
	@Inject
	private SpeakerHasTalkService speakerHasTalkService;

	@POST
	@Path("/speaker/{speakerId:[0-9][0-9]*}/assign/{talkId:[0-9][0-9]*}")
	@Override
	public Response assign(@PathParam("speakerId") String speakerId, @PathParam("talkId") String talkId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@DELETE
	@Path("/speaker/{speakerId:[0-9][0-9]*}/unassign/{talkId:[0-9][0-9]*}")
	@Override
	public Response unassign(@PathParam("speakerId") String speakerId, @PathParam("talkId") String talkId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
