package com.prodyna.pac.conference.service.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.service.exception.RoomNotAvailableException;
import com.prodyna.pac.conference.service.exception.TalkNotFoundException;
import com.prodyna.pac.conference.service.model.Talk;

@Local
public interface TalkService {

	void createTalk(Talk talk) throws RoomNotAvailableException;
	
	Talk updateTalk(Talk talk) throws RoomNotAvailableException, TalkNotFoundException;

	void deleteTalk(long id) throws TalkNotFoundException;

	Talk findTalkById(long id) throws TalkNotFoundException;

	List<Talk> findTalksByName(String name);
	
	List<Talk> findTalksByConferenceId(long conferenceId);
	
	List<Talk> findTalksByRoomId(long roomId);
	
	List<Talk> findAll();
	
}
