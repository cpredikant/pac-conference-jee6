package com.prodyna.pac.conference.api;

import java.util.List;

import javax.ejb.Local;

import com.prodyna.pac.conference.exception.RoomNotFoundException;
import com.prodyna.pac.conference.model.Room;

@Local
public interface RoomService {

	void createRoom(Room room);
	
	Room updateRoom(Room room) throws RoomNotFoundException;

	void deleteRoom(long id) throws RoomNotFoundException;
	
	List<Room> findRoomsByConferenceId(long conferenceId);

	Room findRoomById(long id) throws RoomNotFoundException;

	List<Room> findRoomsByName(String name);
	
	List<Room> findAll();

}
