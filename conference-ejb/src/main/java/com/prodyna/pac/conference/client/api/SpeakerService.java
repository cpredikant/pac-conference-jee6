package com.prodyna.pac.conference.client.api;

import javax.ejb.Local;

import com.prodyna.pac.conference.client.model.Speaker;

@Local
public interface SpeakerService {

	void saveSpeaker(Speaker speaker);

	void deleteSpeaker(Speaker speaker);

	Speaker findSpeakerById(long id);

	Speaker findSpeakerByName(String name);
}
