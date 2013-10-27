package com.prodyna.pac.conference.client.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.client.model.Speaker;
import com.prodyna.pac.conference.client.model.SpeakerHasTalk;
import com.prodyna.pac.conference.client.model.Talk;

@Local
public interface SpeakerHasTalkService {

	void assign(Speaker speaker, Talk talk);
	
	void unassign(Speaker speaker, Talk talk);
	
	SpeakerHasTalk findSpeakerHasTalkBySpeakerAndTalk(Speaker speaker, Talk talk);
	
	void unassignTalksBySpeaker(Speaker speaker);
	
	void unassignSpeakersByTalk(Talk talk);
	
	List<Speaker> findSpeakersByTalk(Talk talk);
	
	List<Talk> findTalksBySpeaker(Speaker speaker);
}
