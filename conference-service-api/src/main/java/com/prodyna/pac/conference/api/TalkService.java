package com.prodyna.pac.conference.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.model.Talk;

@Local
public interface TalkService {

	void createTalk(Talk talk);
	
	Talk updateTalk(Talk talk);

	void deleteTalk(Talk talk);

	Talk findTalkById(long id);

	List<Talk> findTalksByName(String name);
	
	List<Talk> findTalksByConferenceId(long conferenceId);
	
	List<Talk> findTalksByRoomId(long roomId);
	
	List<Talk> findAll();
	
}
