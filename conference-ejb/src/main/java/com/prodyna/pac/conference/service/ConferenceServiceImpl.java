package com.prodyna.pac.conference.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.prodyna.pac.conference.client.api.ConferenceService;
import com.prodyna.pac.conference.client.model.Conference;

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
	public Conference updateConference(Conference conference) {

		Conference updatedConference = conference;

		if (!em.contains(conference)) {
			updatedConference = em.merge(conference);
		}

		return updatedConference;
	}

	@Override
	public void deleteConference(Conference conference) {

		Conference conferenceToDelete = findConferenceById(conference.getId());

		if (conferenceToDelete != null) {
			em.remove(conferenceToDelete);
		}
	}

	@Override
	public Conference findConferenceById(long id) {

		TypedQuery<Conference> query = em.createNamedQuery(
				"Conference.findConferenceById", Conference.class);
		query.setParameter("id", id);

		Conference conference = null;

		try {
			conference = query.getSingleResult();
		} catch (NoResultException exception) {
			log.info("No result for Entity {} with id {}",
					Conference.class.getName(), id);
		}

		return conference;
	}

	@Override
	public List<Conference> findConferenceByName(String name) {

		TypedQuery<Conference> query = em.createNamedQuery(
				"Conference.findConferenceByName", Conference.class);
		query.setParameter("name", name);

		return query.getResultList();
	}

	@Override
	public List<Conference> findAll() {
		TypedQuery<Conference> query = em.createNamedQuery(
				"Conference.findAll", Conference.class);
		return query.getResultList();
	}

}
