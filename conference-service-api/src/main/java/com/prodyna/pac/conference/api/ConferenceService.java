package com.prodyna.pac.conference.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.exception.ConferenceNotFoundException;
import com.prodyna.pac.conference.model.Conference;

@Local
public interface ConferenceService {

	void createConference(Conference conference);
	
	Conference updateConference(Conference conference) throws ConferenceNotFoundException;
	
	void deleteConference(Conference conference) throws ConferenceNotFoundException;
	
	Conference findConferenceById(long id) throws ConferenceNotFoundException;
	
	List<Conference> findConferenceByName(String name);
	
	List<Conference> findAll();
	
}
