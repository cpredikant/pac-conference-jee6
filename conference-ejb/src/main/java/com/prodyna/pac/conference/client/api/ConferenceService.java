package com.prodyna.pac.conference.client.api;

import javax.ejb.Local;

import com.prodyna.pac.conference.client.model.Conference;

@Local
public interface ConferenceService {

	void saveConference(Conference conference);
	
	void deleteConference(Conference conference);
	
	Conference findConferenceById(long id);
	
	Conference findConferenceByName(String name);
	
}
