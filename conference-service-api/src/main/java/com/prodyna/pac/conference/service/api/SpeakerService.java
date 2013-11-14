package com.prodyna.pac.conference.service.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.service.exception.SpeakerNotFoundException;
import com.prodyna.pac.conference.service.model.Speaker;

@Local
public interface SpeakerService {

	void createSpeaker(Speaker speaker);
	
	Speaker updateSpeaker(Speaker speaker) throws SpeakerNotFoundException;

	void deleteSpeaker(long id) throws SpeakerNotFoundException;

	Speaker findSpeakerById(long id) throws SpeakerNotFoundException;

	List<Speaker> findSpeakersByName(String name);
	
	List<Speaker> findAll();
}
