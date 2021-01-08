package de.predikant.conference.service;

import de.predikant.conference.service.api.ConferenceService;
import de.predikant.conference.service.exception.ConferenceNotFoundException;
import de.predikant.conference.service.interceptor.Logging;
import de.predikant.conference.service.interceptor.Performance;
import de.predikant.conference.service.model.Conference;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
@Performance
@Timed
public class ConferenceServiceImpl implements ConferenceService, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Override
    @Logging
    public void createConference(final Conference conference) {

        em.persist(conference);
        em.flush();

    }

    @Override
    @Logging
    public Conference updateConference(final Conference conference)
            throws ConferenceNotFoundException {

        Conference updatedConference = findConferenceById(conference.getId());

        if (!em.contains(conference)) {
            updatedConference = em.merge(conference);
            em.flush();
        }

        return updatedConference;
    }

    @Override
    @Logging
    public void deleteConference(final long id)
            throws ConferenceNotFoundException {

        final Conference conferenceToDelete = findConferenceById(id);

        if (conferenceToDelete != null) {
            em.remove(conferenceToDelete);
            em.flush();
        }
    }

    @Override
    @Logging
    public Conference findConferenceById(final long id)
            throws ConferenceNotFoundException {

        final TypedQuery<Conference> query = em.createNamedQuery(
                "Conference.findConferenceById", Conference.class);
        query.setParameter("id", id);

        Conference conference = null;

        try {
            conference = query.getSingleResult();
        } catch (final NoResultException exception) {
            log.info("No result for Entity {} with id {}",
                    Conference.class.getName(), id);
            throw new ConferenceNotFoundException("Conference with id " + id
                    + " not found.");
        }

        return conference;
    }

    @Override
    @Logging
    public List<Conference> findConferenceByName(final String name) {

        final TypedQuery<Conference> query = em.createNamedQuery(
                "Conference.findConferenceByName", Conference.class);
        query.setParameter("name", name);

        return query.getResultList();
    }

    @Override
    @Logging
    public List<Conference> findAll() {
        final TypedQuery<Conference> query = em.createNamedQuery(
                "Conference.findAll", Conference.class);
        return query.getResultList();
    }

}
