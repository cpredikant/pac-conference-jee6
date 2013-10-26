package com.prodyna.pac.conference.client.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.client.model.Speaker;

@Local
public interface SpeakerService {

	void createSpeaker(Speaker speaker);
	
	Speaker updateSpeaker(Speaker speaker);

	void deleteSpeaker(Speaker speaker);

	Speaker findSpeakerById(long id);

	List<Speaker> findSpeakersByName(String name);
	
	List<Speaker> findAll();
}
