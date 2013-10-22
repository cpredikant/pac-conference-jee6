package com.prodyna.pac.conference.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import com.prodyna.pac.conference.client.api.RoomService;
import com.prodyna.pac.conference.client.model.Room;

@Stateless
public class RoomServiceImpl implements RoomService, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Override
	public void createRoom(Room room) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRoom(Room room) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRoom(Room room) {
		// TODO Auto-generated method stub

	}

	@Override
	public Room findRoomById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room findRoomByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> findRoomsByConferenceId(long conferenceId) {
		// TODO Auto-generated method stub
		return null;
	}

}
