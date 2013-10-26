package com.prodyna.pac.conference.client.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.client.model.Room;

@Local
public interface RoomService {

	void createRoom(Room room);
	
	Room updateRoom(Room room);

	void deleteRoom(Room room);
	
	List<Room> findRoomsByConferenceId(long conferenceId);

	Room findRoomById(long id);

	List<Room> findRoomsByName(String name);
	
	List<Room> findAll();

}
