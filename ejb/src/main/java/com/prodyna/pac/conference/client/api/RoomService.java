package com.prodyna.pac.conference.client.api;

import com.prodyna.pac.conference.client.model.Room;

public interface RoomService {

	void createRoom(Room room);

	void updateRoom(Room room);

	void deleteRoom(Room room);

	Room findRoomById(long id);

	Room findRoomByName(String name);

}
