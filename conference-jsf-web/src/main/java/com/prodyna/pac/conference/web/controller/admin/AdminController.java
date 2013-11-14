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
import com.prodyna.pac.conference.service.api.SpeakerService;
import com.prodyna.pac.conference.service.api.TalkService;
import com.prodyna.pac.conference.service.model.Conference;
import com.prodyna.pac.conference.service.model.Room;
import com.prodyna.pac.conference.service.model.Speaker;
import com.prodyna.pac.conference.service.model.Talk;

@Named
@ViewScoped
public class AdminController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private List<Conference> conferences;

	private List<Speaker> speakers;

	private List<Talk> talks;

	private List<Room> rooms;

	@Inject
	private ConferenceService conferenceService;

	@Inject
	private SpeakerService speakerService;

	@Inject
	private TalkService talkService;

	@Inject
	private RoomService roomService;

	@PostConstruct
	public void init() {
		conferences = conferenceService.findAll();
		speakers = speakerService.findAll();
		talks = talkService.findAll();
		rooms = roomService.findAll();
	}

	public List<Conference> getConferences() {
		return conferences;
	}

	public void setConferences(List<Conference> conferences) {
		this.conferences = conferences;
	}

	public List<Speaker> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}

	public void deleteConference(long id) {
		try {
			conferenceService.deleteConference(id);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Conference deleted"));
		} catch (Exception e) {
			logger.error("Error deleting Conference with id {}", id);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
							"Error deleting Conference"));
		}
		
		conferences = conferenceService.findAll();
	}

	public void deleteSpeaker(long id) {
		try {
			speakerService.deleteSpeaker(id);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Speaker deleted"));
		} catch (Exception e) {
			logger.error("Error deleting Speaker with id {}", id);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
							"Error deleting Speaker"));
		}
		speakers = speakerService.findAll();
	}

	public void deleteRoomAction(long id) {
		try {
			roomService.deleteRoom(id);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Room deleted"));
		} catch (Exception e) {
			logger.error("Error deleting Room with id {}", id, e);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error deleting Room"));
		}
		rooms = roomService.findAll();
	}

	public void deleteTalkAction(long id) {
		try {
			talkService.deleteTalk(id);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Talk deleted"));
		} catch (Exception e) {
			logger.error("Error deleting Talk with id {}", id, e);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error deleting Talk"));
		}
		talks = talkService.findAll();
	}

	public List<Talk> getTalks() {
		return talks;
	}

	public void setTalks(List<Talk> talks) {
		this.talks = talks;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}
