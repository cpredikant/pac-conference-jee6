package com.prodyna.pac.conference.client.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.client.model.Room;

@Local
public interface RoomService {

	void saveRoom(Room room);

	void deleteRoom(Room room);
	
	List<Room> findRoomsByConferenceId(long conferenceId);

	Room findRoomById(long id);

	Room findRoomByName(String name);

}
