package com.prodyna.pac.conference.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.exception.RoomNotAvailableException;
import com.prodyna.pac.conference.exception.TalkNotFoundException;
import com.prodyna.pac.conference.model.Talk;

@Local
public interface TalkService {

	void createTalk(Talk talk) throws RoomNotAvailableException;
	
	Talk updateTalk(Talk talk) throws RoomNotAvailableException, TalkNotFoundException;

	void deleteTalk(Talk talk) throws TalkNotFoundException;

	Talk findTalkById(long id) throws TalkNotFoundException;

	List<Talk> findTalksByName(String name);
	
	List<Talk> findTalksByConferenceId(long conferenceId);
	
	List<Talk> findTalksByRoomId(long roomId);
	
	List<Talk> findAll();
	
}