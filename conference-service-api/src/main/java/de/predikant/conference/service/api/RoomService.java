package de.predikant.conference.service.api;

import java.util.List;

import javax.ejb.Local;

import de.predikant.conference.service.exception.RoomNotFoundException;
import de.predikant.conference.service.model.Room;

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
