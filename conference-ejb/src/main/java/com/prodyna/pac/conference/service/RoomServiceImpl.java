package com.prodyna.pac.conference.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.pac.conference.client.api.RoomService;
import com.prodyna.pac.conference.client.model.Room;

@Stateless
public class RoomServiceImpl implements RoomService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Logger log;
	
	@Inject
	private EntityManager em;
	
	@Override
	public void saveRoom(Room room) {
		
		if (room.getId() > 0){
			em.merge(room);
		} else {
			em.persist(room);
		}
		
		
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
