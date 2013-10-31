package com.prodyna.pac.conference.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.prodyna.pac.conference.api.TalkService;
import com.prodyna.pac.conference.common.util.DateUtil;
import com.prodyna.pac.conference.exception.RoomNotAvailableException;
import com.prodyna.pac.conference.exception.TalkNotFoundException;
import com.prodyna.pac.conference.model.Talk;

@Stateless
public class TalkServiceImpl implements TalkService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Override
	public void createTalk(Talk talk) {

		roomIsAvailable(talk);

		em.persist(talk);
	}

	@Override
	public Talk updateTalk(Talk talk) {
		Talk updatedTalk = talk;

		roomIsAvailable(talk);

		if (!em.contains(talk)) {
			updatedTalk = em.merge(talk);
		}

		return updatedTalk;
	}

	@Override
	public void deleteTalk(Talk talk) {
		Talk talkToDelete = findTalkById(talk.getId());

		if (talkToDelete != null) {
			em.remove(talkToDelete);
		}
	}

	@Override
	public Talk findTalkById(long id) {
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
	public List<Talk> findTalksByName(String name) {
		TypedQuery<Talk> query = em.createNamedQuery("Talk.findTalkByName",
				Talk.class);
		query.setParameter("name", name);

		return query.getResultList();
	}

	@Override
	public List<Talk> findTalksByConferenceId(long conferenceId) {
		TypedQuery<Talk> query = em.createNamedQuery(
				"Talk.findTalksByConferenceId", Talk.class);
		query.setParameter("id", conferenceId);

		return query.getResultList();
	}

	@Override
	public List<Talk> findTalksByRoomId(long roomId) {
		TypedQuery<Talk> query = em.createNamedQuery("Talk.findTalksByRoomId",
				Talk.class);
		query.setParameter("id", roomId);

		return query.getResultList();
	}

	@Override
	public List<Talk> findAll() {
		TypedQuery<Talk> query = em
				.createNamedQuery("Talk.findAll", Talk.class);
		return query.getResultList();
	}

	private void roomIsAvailable(Talk talk) {
		
		if (null == talk.getRoom()){
			return;
		}
		
		List<Talk> talks = findTalksByRoomId(talk.getRoom().getId());
		
		for (Talk t: talks){
			Date start = talk.getStart();

			Date end = DateUtil.addMinutesToDate(start, talk.getDuration());
			
			if (start.after(DateUtil.addMinutesToDate(t.getStart(),
					t.getDuration()))
					|| end.before(t.getStart())) {
				throw new RoomNotAvailableException("Room "
						+ talk.getRoom().getName() + " is not available for talk "
						+ talk.getName());
			}
		}
	}

}
