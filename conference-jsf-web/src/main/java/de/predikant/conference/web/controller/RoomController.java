package de.predikant.conference.web.controller;

import de.predikant.conference.service.api.RoomService;
import de.predikant.conference.service.api.TalkService;
import de.predikant.conference.service.exception.RoomNotFoundException;
import de.predikant.conference.service.model.Room;
import de.predikant.conference.service.model.Talk;
import org.slf4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RoomController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger logger;

    @Inject
    private RoomService roomService;

    @Inject
    private TalkService talkService;

    private long roomId;

    private Room room;

    private List<Talk> talks;

    public void initViewParams() {
        room = loadRoom(roomId);
        talks = loadTalks(roomId);
    }

    private List<Talk> loadTalks(long id) {
        return talkService.findTalksByRoomId(id);
    }

    private Room loadRoom(long id) {
        Room r = null;

        try {
            r = roomService.findRoomById(id);
        } catch (RoomNotFoundException e) {
            logger.error("Room with id {} not Found", id);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
                            "Room not Found"));
        }

        return r;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }

}
