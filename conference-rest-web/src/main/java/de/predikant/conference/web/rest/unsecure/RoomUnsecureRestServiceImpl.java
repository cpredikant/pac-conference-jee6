/**
 * 
 */
package de.predikant.conference.web.rest.unsecure;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import de.predikant.conference.service.api.RoomService;
import de.predikant.conference.service.exception.RoomNotFoundException;
import de.predikant.conference.service.model.Room;
import de.predikant.conference.web.rest.api.unsecure.RoomUnsecureRestService;

@Path("/public/room")
@RequestScoped
public class RoomUnsecureRestServiceImpl implements RoomUnsecureRestService {

	@Inject
	private RoomService roomService;
	
	@Inject
	private Logger logger;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.predikant.conference.web.rest.api.PublicRestService#listAllTalks()
	 */
	
	@Override
	public Response listAllRooms() {
		List<Room> rooms = null;
		try {
			rooms = roomService.findAll();

		} catch (Exception e) {
			logger.error("Error listing all Rooms", e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(rooms).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.predikant.conference.web.rest.api.PublicRestService#listTalkById
	 * (java.lang.String)
	 */
	
	@Override
	public Response listRoomById(String id) {
		Room room = null;
		try {
			room = roomService.findRoomById(Long.valueOf(id));
		} catch (RoomNotFoundException e) {
			logger.error("Room not Found by id " + id, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error find Room by id " + id, e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(room).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.predikant.conference.web.rest.api.PublicRestService#listTalkByName
	 * (java.lang.String)
	 */
	
	@Override
	public Response listRoomsByName( String name) {
		List<Room> rooms = null;
		try {
			rooms = roomService.findRoomsByName(name);

		} catch (Exception e) {
			logger.error("Error finding Rooms by name " + name, e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(rooms).build();
	}

}
