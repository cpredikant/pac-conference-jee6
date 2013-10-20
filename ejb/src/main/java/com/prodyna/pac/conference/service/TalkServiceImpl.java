package com.prodyna.pac.conference.service;

import java.util.List;

import javax.ejb.Stateless;

import com.prodyna.pac.conference.client.api.TalkService;
import com.prodyna.pac.conference.client.model.Talk;

@Stateless
public class TalkServiceImpl implements TalkService {

	@Override
	public void createTalk(Talk talk) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTalk(Talk talk) {
		// TODO Auto-generated method stub

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
