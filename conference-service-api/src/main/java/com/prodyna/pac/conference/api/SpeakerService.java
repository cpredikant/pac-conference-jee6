package com.prodyna.pac.conference.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.exception.SpeakerNotFoundException;
import com.prodyna.pac.conference.model.Speaker;

@Local
public interface SpeakerService {

	void createSpeaker(Speaker speaker);
	
	Speaker updateSpeaker(Speaker speaker) throws SpeakerNotFoundException;

	void deleteSpeaker(long id) throws SpeakerNotFoundException;

	Speaker findSpeakerById(long id) throws SpeakerNotFoundException;

	List<Speaker> findSpeakersByName(String name);
	
	List<Speaker> findAll();
}
