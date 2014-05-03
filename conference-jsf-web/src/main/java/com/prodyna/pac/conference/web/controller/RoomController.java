package com.prodyna.pac.conference.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.prodyna.pac.conference.service.api.RoomService;
import com.prodyna.pac.conference.service.api.TalkService;
import com.prodyna.pac.conference.service.exception.RoomNotFoundException;
import com.prodyna.pac.conference.service.model.Room;
import com.prodyna.pac.conference.service.model.Talk;

@Named
@ViewScoped
public class RoomController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private long roomId;

	private Room room;

	@Inject
	private RoomService roomService;

	private List<Talk> talks;

	@Inject
	private TalkService talkService;

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
