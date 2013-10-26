package com.prodyna.pac.conference.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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
	public void createSpeaker(Speaker speaker) {
		em.persist(speaker);

	}

	@Override
	public Speaker updateSpeaker(Speaker speaker) {
		Speaker updatedSpeaker = speaker;

		if (!em.contains(speaker)) {
			updatedSpeaker = em.merge(speaker);
		}

		return updatedSpeaker;
	}

	@Override
	public void deleteSpeaker(Speaker speaker) {
		Speaker speakerToDelete = findSpeakerById(speaker.getId());

		if (speakerToDelete != null) {
			em.remove(speakerToDelete);
		}
	}

	@Override
	public Speaker findSpeakerById(long id) {
		TypedQuery<Speaker> query = em.createNamedQuery(
				"Speaker.findSpeakerById", Speaker.class);
		query.setParameter("id", id);

		Speaker speaker = null;

		try {
			speaker = query.getSingleResult();
		} catch (NoResultException exception) {
			log.info("No result for Entity {} with id {}",
					Speaker.class.getName(), id);
		}

		return speaker;
	}

	@Override
	public List<Speaker> findSpeakersByName(String name) {
		TypedQuery<Speaker> query = em.createNamedQuery(
				"Speaker.findSpeakersByName", Speaker.class);
		query.setParameter("name", name);

		return query.getResultList();
	}

	@Override
	public List<Speaker> findAll() {
		TypedQuery<Speaker> query = em.createNamedQuery("Speaker.findAll",
				Speaker.class);
		return query.getResultList();
	}

}
