package com.prodyna.pac.conference.api;

import javax.ejb.Local;

import com.prodyna.pac.conference.exception.SpeakerHasTalkNotFoundException;
import com.prodyna.pac.conference.exception.SpeakerNotAvailableException;
import com.prodyna.pac.conference.model.Speaker;
import com.prodyna.pac.conference.model.SpeakerHasTalk;
import com.prodyna.pac.conference.model.Talk;

@Local
public interface SpeakerHasTalkService {

	void assign(Speaker speaker, Talk talk) throws SpeakerNotAvailableException;
	
	void unassign(Speaker speaker, Talk talk)
			throws SpeakerHasTalkNotFoundException;
	
	SpeakerHasTalk findSpeakerHasTalkBySpeakerAndTalk(Speaker speaker, Talk talk)
			throws SpeakerHasTalkNotFoundException;
	

	
}
