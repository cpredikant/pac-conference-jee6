package de.predikant.conference.web.rest.api.secure;

import de.predikant.conference.service.model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/private/room")
public interface RoomSecureRestService {

    @POST
    Response createRoom(Room room);

    @PUT
    Response updateRoom(Room room);

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    Response deleteRoom(@PathParam("id") String id);

}
