package com.prodyna.pac.conference.web.rest;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;


@Path("/rooms")
@RequestScoped
public class RoomRESTService implements Serializable {

//	private static final long serialVersionUID = 1L;
//
//	@Inject
//	private RoomService roomService;
//
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response createRoom(Room room) {
//
//		roomService.createRoom(room);
//
//		return Response.ok(room).build();
//		
//	}
//
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response updateRoom(Room room) {
//
//		Room updatedRoom = roomService.updateRoom(room);
//
//		return Response.ok(updatedRoom).build();
//
//	}
//
//	@DELETE
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response deleteRoom(Room room) {
//
//		roomService.deleteRoom(room);
//
//		return Response.ok().build();
//
//	}
//
//	@GET
//	@Path("/byId/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response findRoomById(@PathParam("id") long id) {
//
//		Room room = roomService.findRoomById(id);
//
//		Response response;
//
//		if (room != null) {
//			response = Response.ok().entity(room).build();
//		} else {
//			response = Response.noContent().build();
//		}
//
//		return response;
//
//	}
//
//	@GET
//	@Path("/byName/{name}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response listRoomsByName(@PathParam("name") String name) {
//
//		List<Room> rooms = roomService
//				.findRoomsByName(name);
//
//		Response response;
//
//		if (rooms != null && rooms.size() > 0) {
//			response = Response.ok().entity(rooms).build();
//		} else {
//			response = Response.noContent().build();
//		}
//
//		return response;
//	}
//
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response listAll() {
//
//		List<Room> rooms = roomService.findAll();
//		
//		Response response;
//
//		if (rooms != null && rooms.size() > 0) {
//			response = Response.ok().entity(rooms).build();
//		} else {
//			response = Response.noContent().build();
//		}
//
//		return response;
//
//	}

}
