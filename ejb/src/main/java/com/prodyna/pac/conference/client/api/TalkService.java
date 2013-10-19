package com.prodyna.pac.conference.client.api;

import java.util.List;

import com.prodyna.pac.conference.client.model.Talk;

public interface TalkService {

	void createTalk(Talk talk);

	void updateTalk(Talk talk);

	void deleteTalk(Talk talk);

	Talk findTalkById(long id);

	Talk findTalkByName(String name);
	
	List<Talk> findTalksByConferenceId(long conferenceId);
	
	List<Talk> findTalksByRoomId(long roomId);
	
}
