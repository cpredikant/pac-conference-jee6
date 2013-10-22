package com.prodyna.pac.conference.client.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.client.model.Speaker;
import com.prodyna.pac.conference.client.model.Talk;

@Local
public interface SpeakerHasTalkService {

	void assign(long speakerId, long talkId);
	
	void unassign(long speakerId, long talkId);
	
	void unassignTalksBySpeakerId(long speakerId);
	
	void unassignSpeakersByTalkId(long talkId);
	
	List<Speaker> findSpeakersByTalkId(long talkId);
	
	List<Talk> findTalksBySpeakerId(long speakerId);
}
