package com.prodyna.pac.conference.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.exception.SpeakerHasTalkNotFoundException;
import com.prodyna.pac.conference.exception.SpeakerNotAvailableException;
import com.prodyna.pac.conference.model.Speaker;
import com.prodyna.pac.conference.model.SpeakerHasTalk;
import com.prodyna.pac.conference.model.Talk;

@Local
public interface SpeakerHasTalkService {

	void assign(Speaker speaker, Talk talk) throws SpeakerNotAvailableException, SpeakerHasTalkNotFoundException;
	
	void unassign(Speaker speaker, Talk talk)
			throws SpeakerHasTalkNotFoundException;
	
	SpeakerHasTalk findSpeakerHasTalkBySpeakerAndTalk(Speaker speaker, Talk talk)
			throws SpeakerHasTalkNotFoundException;
	
	void unassignTalksBySpeaker(Speaker speaker);
	
	void unassignSpeakersByTalk(Talk talk);
	
	List<Speaker> findSpeakersByTalk(Talk talk);
	
	List<Talk> findTalksBySpeaker(Speaker speaker);
}
