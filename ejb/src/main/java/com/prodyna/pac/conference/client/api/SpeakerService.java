package com.prodyna.pac.conference.client.api;

import com.prodyna.pac.conference.client.model.Speaker;

public interface SpeakerService {

	void createSpeaker(Speaker speaker);

	void updateSpeaker(Speaker speaker);

	void deleteSpeaker(Speaker speaker);

	Speaker findSpeakerById(long id);

	Speaker findSpeakerByName(String name);
}
