package com.prodyna.pac.conference.web.rest.api.secure;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.prodyna.pac.conference.model.Room;


@Path("/private")
public interface RoomSecureRestService {

	public Response createRoom(Room room);

	public Response updateRoom(Room room);

	public Response deleteRoom(String id);

}
