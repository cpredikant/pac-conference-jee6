package com.prodyna.pac.conference.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import com.prodyna.pac.conference.client.api.SpeakerHasTalkService;
import com.prodyna.pac.conference.client.model.Speaker;
import com.prodyna.pac.conference.client.model.Talk;

@Stateless
public class SpeakerHasTalkServiceImpl implements SpeakerHasTalkService, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void assign(long speakerId, long talkId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unassign(long speakerId, long talkId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unassignTalksBySpeakerId(long speakerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unassignSpeakersByTalkId(long talkId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Speaker> findSpeakersByTalkId(long talkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Talk> findTalksBySpeakerId(long speakerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
