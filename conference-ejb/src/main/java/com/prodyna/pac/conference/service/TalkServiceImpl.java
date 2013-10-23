package com.prodyna.pac.conference.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.pac.conference.client.api.TalkService;
import com.prodyna.pac.conference.client.model.Talk;

@Stateless
public class TalkServiceImpl implements TalkService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Override
	public void saveTalk(Talk talk) {
		
		if (talk.getId() > 0) {
			em.merge(talk);
		} else {
			em.persist(talk);
		}

	}

	@Override
	public void deleteTalk(Talk talk) {
		// TODO Auto-generated method stub

	}

	@Override
	public Talk findTalkById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Talk findTalkByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Talk> findTalksByConferenceId(long conferenceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Talk> findTalksByRoomId(long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

}
