package com.prodyna.pac.conference.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.pac.conference.client.api.ConferenceService;
import com.prodyna.pac.conference.client.model.Conference;

public class ConferenceServiceImpl implements ConferenceService {
	
	@Inject
	private Logger log;
	
	@Inject
	private EntityManager em;

	@Override
	public void createConference(Conference conference) {
		em.persist(conference);
	}

	@Override
	public void updateConference(Conference conference) {
		
		if (conference.getId() == 0){
			em.merge(conference);
		}
		
		em.persist(conference);
	}

	@Override
	public void deleteConference(Conference conference) {
		if (conference.getId() == 0){
			em.merge(conference);
		}
		em.remove(conference);

	}

	@Override
	public Conference findConferenceById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conference findConferenceByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
