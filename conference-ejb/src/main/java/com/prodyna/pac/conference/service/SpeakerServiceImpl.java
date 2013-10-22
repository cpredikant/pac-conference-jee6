package com.prodyna.pac.conference.service;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.prodyna.pac.conference.client.api.SpeakerService;
import com.prodyna.pac.conference.client.model.Speaker;

@Stateless
public class SpeakerServiceImpl implements SpeakerService, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void createSpeaker(Speaker speaker) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSpeaker(Speaker speaker) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSpeaker(Speaker speaker) {
		// TODO Auto-generated method stub

	}

	@Override
	public Speaker findSpeakerById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Speaker findSpeakerByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
