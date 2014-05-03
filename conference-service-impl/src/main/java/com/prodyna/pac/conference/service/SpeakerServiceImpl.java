package com.prodyna.pac.conference.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.prodyna.pac.conference.service.api.SpeakerService;
import com.prodyna.pac.conference.service.exception.SpeakerNotFoundException;
import com.prodyna.pac.conference.service.interceptor.Logging;
import com.prodyna.pac.conference.service.interceptor.Performance;
import com.prodyna.pac.conference.service.model.Speaker;

@Stateless
@Performance
public class SpeakerServiceImpl implements SpeakerService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Override
	@Logging
	public void createSpeaker(Speaker speaker) {
		em.persist(speaker);
		em.flush();

	}

	@Override
	@Logging
	public Speaker updateSpeaker(Speaker speaker) throws SpeakerNotFoundException {
		Speaker updatedSpeaker = findSpeakerById(speaker.getId());

		if (!em.contains(speaker)) {
			updatedSpeaker = em.merge(speaker);
			em.flush();
		}

		return updatedSpeaker;
	}

	@Override
	@Logging
	public void deleteSpeaker(long id) throws SpeakerNotFoundException {
		Speaker speakerToDelete = findSpeakerById(id);

		if (speakerToDelete != null) {
			em.remove(speakerToDelete);
			em.flush();
		}
	}

	@Override
	@Logging
	public Speaker findSpeakerById(long id) throws SpeakerNotFoundException {
		TypedQuery<Speaker> query = em.createNamedQuery(
				"Speaker.findSpeakerById", Speaker.class);
		query.setParameter("id", id);

		Speaker speaker = null;

		try {
			speaker = query.getSingleResult();
		} catch (NoResultException exception) {
			log.info("No result for Entity {} with id {}",
					Speaker.class.getName(), id);
			throw new SpeakerNotFoundException("Speaker with id " + id
					+ " not found");

		}

		return speaker;
	}

	@Override
	@Logging
	public List<Speaker> findSpeakersByName(String name) {
		TypedQuery<Speaker> query = em.createNamedQuery(
				"Speaker.findSpeakersByName", Speaker.class);
		query.setParameter("name", name);

		return query.getResultList();
	}

	@Override
	@Logging
	public List<Speaker> findAll() {
		TypedQuery<Speaker> query = em.createNamedQuery("Speaker.findAll",
				Speaker.class);
		return query.getResultList();
	}

}
