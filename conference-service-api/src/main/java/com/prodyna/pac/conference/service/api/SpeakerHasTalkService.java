package com.prodyna.pac.conference.service.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.service.exception.SpeakerHasTalkNotFoundException;
import com.prodyna.pac.conference.service.exception.SpeakerNotAvailableException;
import com.prodyna.pac.conference.service.model.Speaker;
import com.prodyna.pac.conference.service.model.SpeakerHasTalk;
import com.prodyna.pac.conference.service.model.Talk;

@Local
public interface SpeakerHasTalkService {

	void assign(Speaker speaker, Talk talk) throws SpeakerNotAvailableException;
	
	void unassign(Speaker speaker, Talk talk)
			throws SpeakerHasTalkNotFoundException;
	
	SpeakerHasTalk findSpeakerHasTalkBySpeakerAndTalk(Speaker speaker, Talk talk)
			throws SpeakerHasTalkNotFoundException;
	
	List<Speaker> findSpeakersByTalk(Talk talk);
	
	List<Talk> findTalksBySpeaker(Speaker speaker);
	
}
