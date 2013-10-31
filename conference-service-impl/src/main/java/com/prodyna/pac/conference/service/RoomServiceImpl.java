package com.prodyna.pac.conference.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.prodyna.pac.conference.api.RoomService;
import com.prodyna.pac.conference.exception.RoomNotFoundException;
import com.prodyna.pac.conference.model.Room;

@Stateless
public class RoomServiceImpl implements RoomService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Override
	public void createRoom(Room room) {
		em.persist(room);
	}

	@Override
	public Room updateRoom(Room room) {

		Room updatedRoom = room;

		if (!em.contains(room)) {
			updatedRoom = em.merge(room);
		}

		return updatedRoom;
	}

	@Override
	public void deleteRoom(Room room) {
		Room roomToDelete = findRoomById(room.getId());

		if (roomToDelete != null) {
			em.remove(roomToDelete);
		}

	}

	@Override
	public Room findRoomById(long id) {

		TypedQuery<Room> query = em.createNamedQuery("Room.findRoomById",
				Room.class);
		query.setParameter("id", id);

		Room room = null;

		try {
			room = query.getSingleResult();
		} catch (NoResultException exception) {
			log.info("No result for Entity {} with id {}",
					Room.class.getName(), id);
			throw new RoomNotFoundException("Room with id " + id + " not found.");
		}

		return room;
	}

	@Override
	public List<Room> findRoomsByName(String name) {
		TypedQuery<Room> query = em.createNamedQuery("Room.findRoomByName",
				Room.class);
		query.setParameter("name", name);

		return query.getResultList();
	}

	@Override
	public List<Room> findRoomsByConferenceId(long conferenceId) {
		TypedQuery<Room> query = em.createNamedQuery("Room.findRoomsByConferenceId",
				Room.class);
		query.setParameter("id", conferenceId);
		
		return query.getResultList();

	}

	@Override
	public List<Room> findAll() {
		TypedQuery<Room> query = em
				.createNamedQuery("Room.findAll", Room.class);
		return query.getResultList();
	}
	

}
