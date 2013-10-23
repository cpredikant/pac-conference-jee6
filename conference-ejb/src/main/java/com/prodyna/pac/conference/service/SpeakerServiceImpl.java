package com.prodyna.pac.conference.service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.pac.conference.client.api.SpeakerService;
import com.prodyna.pac.conference.client.model.Speaker;

@Stateless
public class SpeakerServiceImpl implements SpeakerService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Logger log;
	
	@Inject
	private EntityManager em;

	@Override
	public void saveSpeaker(Speaker speaker) {
		if (speaker.getId() > 0){
			em.merge(speaker);
		} else {
			em.persist(speaker);
		}

	}


	@Override
	public void deleteSpeaker(Speaker speaker) {
		// TODO Auto-generated method stub

	}

	@Override
	public Speaker findSpeakerById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Speaker findSpeakerByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
