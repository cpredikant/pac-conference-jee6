package de.predikant.conference.service;

import de.predikant.conference.service.api.SpeakerHasTalkService;
import de.predikant.conference.service.exception.SpeakerHasTalkNotFoundException;
import de.predikant.conference.service.exception.SpeakerNotAvailableException;
import de.predikant.conference.service.interceptor.Logging;
import de.predikant.conference.service.interceptor.Performance;
import de.predikant.conference.service.model.Speaker;
import de.predikant.conference.service.model.SpeakerHasTalk;
import de.predikant.conference.service.model.Talk;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
@Performance
@Timed
public class SpeakerHasTalkServiceImpl implements SpeakerHasTalkService,
        Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Override
    @Logging
    public void assign(final Speaker speaker, final Talk talk)
            throws SpeakerNotAvailableException {

        final List<Speaker> speakersByTalk = findSpeakersByTalk(talk);

        if (!speakersByTalk.contains(speaker)) {

            speakerIsAvailable(speaker, talk);

            final SpeakerHasTalk sht = new SpeakerHasTalk();
            sht.setSpeaker(speaker);
            sht.setTalk(talk);
            em.persist(sht);
            em.flush();
        }

    }

    private void speakerIsAvailable(final Speaker speaker, final Talk talk)
            throws SpeakerNotAvailableException {

        final List<Talk> talks = findTalksBySpeaker(speaker);

        final LocalDateTime start = talk.getStart();

        final LocalDateTime end = start.plusMinutes(talk.getDuration());

        for (final Talk t : talks) {

            if (start.isAfter(t.getStart().plusMinutes(t.getDuration()))
                    || end.isAfter(t.getStart())) {
                throw new SpeakerNotAvailableException("Speaker "
                        + speaker.getName() + " is not available for talk "
                        + talk.getName());
            }
        }
    }

    @Override
    @Logging
    public SpeakerHasTalk findSpeakerHasTalkBySpeakerAndTalk(final Speaker speaker,
                                                             final Talk talk) throws SpeakerHasTalkNotFoundException {

        final TypedQuery<SpeakerHasTalk> query = em.createNamedQuery(
                "SpeakerHasTalk.findSpeakerHasTalkBySpeakerAndTalk",
                SpeakerHasTalk.class);

        query.setParameter("speakerId", speaker.getId());
        query.setParameter("talkId", talk.getId());

        SpeakerHasTalk speakerHasTalk = null;

        try {
            speakerHasTalk = query.getSingleResult();
        } catch (final NoResultException exception) {
            log.info("No result for Entity {} for Speaker {} and Talk {}",
                    new Object[]{SpeakerHasTalk.class.getName(), speaker,
                            talk});
            throw new SpeakerHasTalkNotFoundException(
                    "SpeakerHasTalk not found for Speaker " + speaker
                            + " and Talk " + talk);
        }

        return speakerHasTalk;

    }

    @Override
    @Logging
    public void unassign(final Speaker speaker, final Talk talk)
            throws SpeakerHasTalkNotFoundException {

        final SpeakerHasTalk sht = findSpeakerHasTalkBySpeakerAndTalk(speaker, talk);

        em.remove(sht);
        em.flush();

    }

    @Override
    @Logging
    public List<Talk> findTalksBySpeaker(final Speaker speaker) {

        final TypedQuery<Talk> query = em.createNamedQuery(
                "SpeakerHasTalk.findTalksBySpeaker", Talk.class);
        query.setParameter("speaker", speaker);

        return query.getResultList();
    }

    @Override
    @Logging
    public List<Speaker> findSpeakersByTalk(final Talk talk) {
        final TypedQuery<Speaker> query = em.createNamedQuery(
                "SpeakerHasTalk.findSpeakersByTalk", Speaker.class);
        query.setParameter("talk", talk);

        return query.getResultList();
    }

}
