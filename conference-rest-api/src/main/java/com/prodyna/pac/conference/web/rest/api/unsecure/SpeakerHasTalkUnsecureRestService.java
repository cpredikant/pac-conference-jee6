package com.prodyna.pac.conference.web.rest.api.unsecure;

import javax.ws.rs.core.Response;

public interface SpeakerHasTalkUnsecureRestService {

	public Response findSpeakerHasTalkBySpeakerAndTalk(String speakerId, String talkId);

	public Response listSpeakersByTalk(String talkId);

	public Response listTalksBySpeaker(String speakerId);

}