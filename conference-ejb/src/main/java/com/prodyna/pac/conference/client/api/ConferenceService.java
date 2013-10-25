package com.prodyna.pac.conference.client.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.client.model.Conference;

@Local
public interface ConferenceService {

	void createConference(Conference conference);
	
	Conference updateConference(Conference conference);
	
	void deleteConference(Conference conference);
	
	Conference findConferenceById(long id);
	
	List<Conference> findConferenceByName(String name);
	
	List<Conference> findAll();
	
}
