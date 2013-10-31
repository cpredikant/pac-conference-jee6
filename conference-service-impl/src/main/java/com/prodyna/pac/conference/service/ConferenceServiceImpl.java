package com.prodyna.pac.conference.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.prodyna.pac.conference.api.ConferenceService;
import com.prodyna.pac.conference.exception.ConferenceNotFoundException;
import com.prodyna.pac.conference.model.Conference;
import com.prodyna.pac.conference.service.interceptor.Logging;

@Stateless
public class ConferenceServiceImpl implements ConferenceService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Override
	public void createConference(Conference conference) {

		em.persist(conference);

	}

	@Override
	@Logging
	public Conference updateConference(Conference conference)
			throws ConferenceNotFoundException {

		Conference updatedConference = conference;

		if (!em.contains(conference)) {
			updatedConference = em.merge(conference);
		}

		return updatedConference;
	}

	@Override
	@Logging
	public void deleteConference(Conference conference)
			throws ConferenceNotFoundException {

		Conference conferenceToDelete = findConferenceById(conference.getId());

		if (conferenceToDelete != null) {
			em.remove(conferenceToDelete);
		}
	}

	@Override
	@Logging
	public Conference findConferenceById(long id)
			throws ConferenceNotFoundException {

		TypedQuery<Conference> query = em.createNamedQuery(
				"Conference.findConferenceById", Conference.class);
		query.setParameter("id", id);

		Conference conference = null;

		try {
			conference = query.getSingleResult();
		} catch (NoResultException exception) {
			log.info("No result for Entity {} with id {}",
					Conference.class.getName(), id);
			throw new ConferenceNotFoundException("Conference with id " + id
					+ " not found.");
		}

		return conference;
	}

	@Override
	@Logging
	public List<Conference> findConferenceByName(String name) {

		TypedQuery<Conference> query = em.createNamedQuery(
				"Conference.findConferenceByName", Conference.class);
		query.setParameter("name", name);

		return query.getResultList();
	}

	@Override
	@Logging
	public List<Conference> findAll() {
		TypedQuery<Conference> query = em.createNamedQuery(
				"Conference.findAll", Conference.class);
		return query.getResultList();
	}

}
