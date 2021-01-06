/**
 *
 */
package de.predikant.conference.web.rest.secure;

import de.predikant.conference.service.api.RoomService;
import de.predikant.conference.service.exception.RoomNotFoundException;
import de.predikant.conference.service.model.Room;
import de.predikant.conference.web.rest.api.secure.RoomSecureRestService;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@RequestScoped
@Timed
public class RoomSecureRestServiceImpl implements RoomSecureRestService {

    @Inject
    private Logger logger;

    @Inject
    private RoomService roomService;

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

    @Override
    public Response updateRoom(Room room) {
        Room updatedRoom = null;
        try {
            updatedRoom = roomService.updateRoom(room);

        } catch (RoomNotFoundException e) {
            logger.error("Talk not found for update " + room, e);
            throw new NotFoundException(e);
        } catch (Exception e) {
            logger.error("Error updating Room " + room, e);
            throw new NotFoundException(e);
        }

        return Response.ok(updatedRoom).build();
    }

    @Override
    public Response deleteRoom(String id) {
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
