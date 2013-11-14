package com.prodyna.pac.conference.web.controller.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.prodyna.pac.conference.service.api.ConferenceService;
import com.prodyna.pac.conference.service.api.RoomService;
import com.prodyna.pac.conference.service.exception.RoomNotFoundException;
import com.prodyna.pac.conference.service.model.Conference;
import com.prodyna.pac.conference.service.model.Room;

@Named
@ViewScoped
public class EditRoomController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private long roomId;

	private Room room;

	@Inject
	private RoomService roomService;
	
	@Inject
	private ConferenceService conferenceService;
	
	private List<Conference> conferences;
	
	private Conference selectedConference;

	@PostConstruct
	public void init(){
		if(room==null){
			room = new Room();
		}
		conferences = conferenceService.findAll();
	}
	
	public void initViewParams() {
		if (roomId != 0) {
			room = loadRoom(roomId);
			selectedConference = room.getConference();
		}

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

	public void saveRoomAction() {
		if (room.getId() == 0){
			createRoom();
		} else {
			updateRoom();
		}
	}
	
	private void createRoom(){
		try {
			roomService.createRoom(room);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Room created"));
		} catch (Exception e) {
			logger.error("Error updating Room {}", room, e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error creating Room"));
		}
	}
	
	private void updateRoom(){
		try {
			roomService.updateRoom(room);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Room saved"));
		} catch (Exception e) {
			logger.error("Error updating Room {}", room, e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error updating Room"));
		}
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

	public List<Conference> getConferences() {
		return conferences;
	}

	public void setConferences(List<Conference> conferences) {
		this.conferences = conferences;
	}

	public Conference getSelectedConference() {
		return selectedConference;
	}

	public void setSelectedConference(Conference selectedConference) {
		this.selectedConference = selectedConference;
		if (room!=null){
			room.setConference(selectedConference);
		}
	}

}
