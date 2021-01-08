package de.predikant.conference.service;

import de.predikant.conference.service.api.RoomService;
import de.predikant.conference.service.exception.RoomNotFoundException;
import de.predikant.conference.service.interceptor.Logging;
import de.predikant.conference.service.interceptor.Performance;
import de.predikant.conference.service.model.Room;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
@Performance
@Timed
public class RoomServiceImpl implements RoomService, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Override
    @Logging
    public void createRoom(final Room room) {
        em.persist(room);
        em.flush();
    }

    @Override
    @Logging
    public Room updateRoom(final Room room) throws RoomNotFoundException {
        Room updatedRoom = findRoomById(room.getId());

        if (!em.contains(room)) {
            updatedRoom = em.merge(room);
            em.flush();
        }

        return updatedRoom;
    }

    @Override
    @Logging
    public void deleteRoom(final long id) throws RoomNotFoundException {
        final Room roomToDelete = findRoomById(id);

        if (roomToDelete != null) {
            em.remove(roomToDelete);
            em.flush();
        }

    }

    @Override
    @Logging
    public Room findRoomById(final long id) throws RoomNotFoundException {

        final TypedQuery<Room> query = em.createNamedQuery("Room.findRoomById",
                Room.class);
        query.setParameter("id", id);

        Room room = null;

        try {
            room = query.getSingleResult();
        } catch (final NoResultException exception) {
            log.info("No result for Entity {} with id {}",
                    Room.class.getName(), id);
            throw new RoomNotFoundException("Room with id " + id
                    + " not found.");
        }

        return room;
    }

    @Override
    @Logging
    public List<Room> findRoomsByName(final String name) {
        final TypedQuery<Room> query = em.createNamedQuery("Room.findRoomByName",
                Room.class);
        query.setParameter("name", name);

        return query.getResultList();
    }

    @Override
    @Logging
    public List<Room> findRoomsByConferenceId(final long conferenceId) {
        final TypedQuery<Room> query = em.createNamedQuery(
                "Room.findRoomsByConferenceId", Room.class);
        query.setParameter("id", conferenceId);

        return query.getResultList();

    }

    @Override
    @Logging
    public List<Room> findAll() {
        final TypedQuery<Room> query = em
                .createNamedQuery("Room.findAll", Room.class);
        return query.getResultList();
    }

}
