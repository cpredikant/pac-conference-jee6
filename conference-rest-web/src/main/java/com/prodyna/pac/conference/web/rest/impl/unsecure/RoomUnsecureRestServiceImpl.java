/**
 * 
 */
package com.prodyna.pac.conference.web.rest.impl.unsecure;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.InternalServerErrorException;
import org.jboss.resteasy.spi.NotFoundException;
import org.slf4j.Logger;

import com.prodyna.pac.conference.api.RoomService;
import com.prodyna.pac.conference.exception.RoomNotFoundException;
import com.prodyna.pac.conference.model.Room;
import com.prodyna.pac.conference.web.rest.api.unsecure.RoomUnsecureRestService;

@Path("/public")
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
	 * com.prodyna.pac.conference.web.rest.api.PublicRestService#listAllTalks()
	 */
	@GET
	@Path("/room")
	@Produces(MediaType.APPLICATION_JSON)
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
	 * com.prodyna.pac.conference.web.rest.api.PublicRestService#listTalkById
	 * (java.lang.String)
	 */
	@GET
	@Path("/room/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response listRoomById(@PathParam("id") String id) {
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
	 * com.prodyna.pac.conference.web.rest.api.PublicRestService#listTalkByName
	 * (java.lang.String)
	 */
	@GET
	@Path("/room/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response listRoomsByName(@PathParam("name") String name) {
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
