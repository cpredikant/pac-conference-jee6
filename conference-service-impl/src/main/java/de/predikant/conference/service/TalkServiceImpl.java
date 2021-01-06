package de.predikant.conference.service;

import de.predikant.conference.common.util.DateUtil;
import de.predikant.conference.service.api.TalkService;
import de.predikant.conference.service.exception.RoomNotAvailableException;
import de.predikant.conference.service.exception.TalkNotFoundException;
import de.predikant.conference.service.interceptor.Logging;
import de.predikant.conference.service.interceptor.Performance;
import de.predikant.conference.service.model.Talk;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Stateless
@Performance
@Timed
public class TalkServiceImpl implements TalkService, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Override
    @Logging
    public void createTalk(Talk talk) throws RoomNotAvailableException {

        roomIsAvailable(talk);

        em.persist(talk);
        em.flush();
    }

    @Override
    @Logging
    public Talk updateTalk(Talk talk) throws RoomNotAvailableException,
            TalkNotFoundException {
        Talk updatedTalk = findTalkById(talk.getId());

        roomIsAvailable(talk);

        if (!em.contains(talk)) {
            updatedTalk = em.merge(talk);
            em.flush();
        }

        return updatedTalk;
    }

    @Override
    @Logging
    public void deleteTalk(long id) throws TalkNotFoundException {
        Talk talkToDelete = findTalkById(id);

        if (talkToDelete != null) {
            em.remove(talkToDelete);
            em.flush();
        }
    }

    @Override
    @Logging
    public Talk findTalkById(long id) throws TalkNotFoundException {
        TypedQuery<Talk> query = em.createNamedQuery("Talk.findTalkById",
                Talk.class);
        query.setParameter("id", id);

        Talk talk = null;

        try {
            talk = query.getSingleResult();
        } catch (NoResultException exception) {
            log.info("No result for Entity {} with id {}",
                    Talk.class.getName(), id);
            throw new TalkNotFoundException("Talk with id " + id + " not found");
        }
        return talk;
    }

    @Override
    @Logging
    public List<Talk> findTalksByName(String name) {
        TypedQuery<Talk> query = em.createNamedQuery("Talk.findTalkByName",
                Talk.class);
        query.setParameter("name", name);

        return query.getResultList();
    }

    @Override
    @Logging
    public List<Talk> findTalksByConferenceId(long conferenceId) {
        TypedQuery<Talk> query = em.createNamedQuery(
                "Talk.findTalksByConferenceId", Talk.class);
        query.setParameter("id", conferenceId);

        return query.getResultList();
    }

    @Override
    @Logging
    public List<Talk> findTalksByRoomId(long roomId) {
        TypedQuery<Talk> query = em.createNamedQuery("Talk.findTalksByRoomId",
                Talk.class);
        query.setParameter("id", roomId);

        return query.getResultList();
    }

    @Override
    @Logging
    public List<Talk> findAll() {
        TypedQuery<Talk> query = em
                .createNamedQuery("Talk.findAll", Talk.class);
        return query.getResultList();
    }

    private void roomIsAvailable(Talk talk) throws RoomNotAvailableException {

        if (null == talk.getRoom()) {
            return;
        }

        List<Talk> talks = findTalksByRoomId(talk.getRoom().getId());

        for (Talk t : talks) {

            Date start = talk.getStart();

            Date end = DateUtil.addMinutesToDate(start, talk.getDuration());

            if (start.after(DateUtil.addMinutesToDate(t.getStart(),
                    t.getDuration()))
                    || end.after(t.getStart())) {
                if (talk.getId() != talk.getId()) {
                    throw new RoomNotAvailableException("Room "
                            + talk.getRoom().getName()
                            + " is not available for talk " + talk.getName());
                }
            }

        }
    }
}
