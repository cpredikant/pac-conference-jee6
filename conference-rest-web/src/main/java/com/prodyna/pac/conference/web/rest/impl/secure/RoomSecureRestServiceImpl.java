/**
 * 
 */
package com.prodyna.pac.conference.web.rest.impl.secure;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import com.prodyna.pac.conference.web.rest.api.secure.RoomSecureRestService;

@Path("/private")
@RequestScoped
public class RoomSecureRestServiceImpl implements RoomSecureRestService {
	
	@Inject
	private Logger logger;
	
	@Inject
	private RoomService roomService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PrivateRestService#createTalk
	 * (com.prodyna.pac.conference.model.Talk)
	 */
	@POST
	@Path("/room")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response createRoom(Room room) {
		try {
			roomService.createRoom(room);
			
		} catch (Exception e) {
			logger.error("Error create Room " + room, e);
			throw new InternalServerErrorException(e);
		}
		return Response.ok(room).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PrivateRestService#updateTalk
	 * (com.prodyna.pac.conference.model.Talk)
	 */
	@PUT
	@Path("/room")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response updateRoom(Room room) {
		Room updatedRoom = null;
		try {
			updatedRoom = roomService.updateRoom(room);
			
		} catch ( RoomNotFoundException e) {
			logger.error("Talk not found for update " + room, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error updating Room " + room, e);
			throw new NotFoundException(e);
		}

		return Response.ok(updatedRoom).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.pac.conference.web.rest.api.PrivateRestService#deleteTalk
	 * (java.lang.String)
	 */
	@DELETE
	@Path("/room/{id:[0-9][0-9]*}")
	@Override
	public Response deleteRoom(@PathParam("id") String id) {
		try {
			roomService.deleteRoom(Long.valueOf(id));
		} catch (RoomNotFoundException e) {
			logger.error("Room not found for deletion " + id, e);
			throw new NotFoundException(e);
		} catch (Exception e) {
			logger.error("Error deleting Room " + id, e);
			throw new NotFoundException(e);
		}

		return Response.ok().build();
	}

	

}
