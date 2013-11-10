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

import com.prodyna.pac.conference.api.ConferenceService;
import com.prodyna.pac.conference.api.RoomService;
import com.prodyna.pac.conference.api.TalkService;
import com.prodyna.pac.conference.exception.ConferenceNotFoundException;
import com.prodyna.pac.conference.model.Conference;
import com.prodyna.pac.conference.model.Room;
import com.prodyna.pac.conference.model.Talk;

@Named
@ViewScoped
public class EditConferenceController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private long conferenceId;

	private Conference conference;

	@Inject
	private ConferenceService conferenceService;

	@Inject
	private TalkService talkService;

	private List<Talk> talks;

	@Inject
	private RoomService roomService;

	private List<Room> rooms;

	public void initViewParams() {
		if (conferenceId != 0) {
			conference = loadConference(conferenceId);
			talks = talkService.findTalksByConferenceId(conferenceId);
			rooms = roomService.findRoomsByConferenceId(conferenceId);
		}
	}
	
	@PostConstruct
	public void init(){
		if(conference == null){
			conference = new Conference();
		}
	}

	private Conference loadConference(long id) {
		Conference c = null;

		try {
			c = conferenceService.findConferenceById(id);
		} catch (ConferenceNotFoundException e) {
			logger.error("Conference with id {} not Found", id);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
							"Conference not Found"));
		}

		return c;
	}

	public void saveConferenceAction() {
		if (conference.getId() == 0){
			createConfrence();
		} else {
			updateConference();
		}
	}
	
	private void createConfrence(){
		try {
			conferenceService.createConference(conference);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Conference created"));
		} catch (Exception e) {
			logger.error("Error updating Conference {}", conference, e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error creating Conference"));
		}
	}
	
	private void updateConference(){
		try {
			conferenceService.updateConference(conference);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Conference saved"));
		} catch (Exception e) {
			logger.error("Error updating Conference {}", conference, e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error updating Conference"));
		}
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
	}

	public long getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(long conferenceId) {
		this.conferenceId = conferenceId;
	}

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
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
