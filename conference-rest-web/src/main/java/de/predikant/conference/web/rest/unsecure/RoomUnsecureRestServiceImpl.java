/**
 *
 */
package de.predikant.conference.web.rest.unsecure;

import de.predikant.conference.service.api.RoomService;
import de.predikant.conference.service.exception.RoomNotFoundException;
import de.predikant.conference.service.model.Room;
import de.predikant.conference.web.rest.api.unsecure.RoomUnsecureRestService;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
public class RoomUnsecureRestServiceImpl implements RoomUnsecureRestService {

    @Inject
    private RoomService roomService;

    @Inject
    private Logger logger;

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

    @Override
    public Response listRoomsByName(String name) {
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
