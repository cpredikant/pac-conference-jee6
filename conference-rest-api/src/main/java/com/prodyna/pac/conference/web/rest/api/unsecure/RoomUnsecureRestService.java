package com.prodyna.pac.conference.web.rest.api.unsecure;

import javax.ws.rs.core.Response;

public interface RoomUnsecureRestService {

	public Response listAllRooms();

	public Response listRoomById(String id);

	public Response listRoomsByName(String name);

}