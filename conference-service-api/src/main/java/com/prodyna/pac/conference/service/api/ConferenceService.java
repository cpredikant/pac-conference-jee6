package com.prodyna.pac.conference.service.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.service.exception.ConferenceNotFoundException;
import com.prodyna.pac.conference.service.model.Conference;

@Local
public interface ConferenceService {

	void createConference(Conference conference);
	
	Conference updateConference(Conference conference) throws ConferenceNotFoundException;
	
	void deleteConference(long id) throws ConferenceNotFoundException;
	
	Conference findConferenceById(long id) throws ConferenceNotFoundException;
	
	List<Conference> findConferenceByName(String name);
	
	List<Conference> findAll();
	
}
