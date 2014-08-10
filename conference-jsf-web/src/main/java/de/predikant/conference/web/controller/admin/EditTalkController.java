package de.predikant.conference.web.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;
import org.slf4j.Logger;

import de.predikant.conference.service.api.ConferenceService;
import de.predikant.conference.service.api.RoomService;
import de.predikant.conference.service.api.SpeakerHasTalkService;
import de.predikant.conference.service.api.SpeakerService;
import de.predikant.conference.service.api.TalkService;
import de.predikant.conference.service.exception.RoomNotAvailableException;
import de.predikant.conference.service.exception.SpeakerHasTalkNotFoundException;
import de.predikant.conference.service.exception.SpeakerNotAvailableException;
import de.predikant.conference.service.exception.TalkNotFoundException;
import de.predikant.conference.service.model.Conference;
import de.predikant.conference.service.model.Room;
import de.predikant.conference.service.model.Speaker;
import de.predikant.conference.service.model.Talk;

@Named
@ViewScoped
public class EditTalkController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private long talkId;

	private Talk talk;

	@Inject
	private TalkService talkService;

	@Inject
	private ConferenceService conferenceService;

	private List<Conference> conferences;

	private Conference selectedConference;

	@Inject
	private RoomService roomService;

	private List<Room> rooms;

	private Room selectedRoom;

	@Inject
	private SpeakerService speakerService;

	private List<Speaker> speakersSource;

	private List<Speaker> speakersTarget;

	private List<Speaker> initialSpeakers;

	private DualListModel<Speaker> speakersDualListModel;

	@Inject
	private SpeakerHasTalkService speakerHasTalkService;

	@PostConstruct
	public void init() {
		rooms = roomService.findAll();
		conferences = conferenceService.findAll();
		speakersSource = speakerService.findAll();
		
		if (talk == null) {
			talk = new Talk();
			speakersTarget = new ArrayList<Speaker>();
			initialSpeakers = new ArrayList<Speaker>();
			speakersDualListModel = new DualListModel<Speaker>(speakersSource,
					speakersTarget);
		}
		
		
	}

	public void initViewParams() {
		if (talkId != 0) {
			talk = loadTalk(talkId);
			selectedConference = talk.getConference();
			selectedRoom = talk.getRoom();
			speakersTarget = speakerHasTalkService.findSpeakersByTalk(talk);
			initialSpeakers = new ArrayList<Speaker>(speakersTarget);
			speakersSource.removeAll(speakersTarget);
			speakersDualListModel = new DualListModel<Speaker>(speakersSource,
					speakersTarget);
		}
		
	}
	
	private void updateModel(){
		init();
		initViewParams();
	}

	private Talk loadTalk(long id) {
		Talk t = null;

		try {
			t = talkService.findTalkById(id);
		} catch (TalkNotFoundException e) {
			logger.error("Talk with id {} not Found", id);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
							"Talk not Found"));
		}

		return t;
	}

	public void saveTalkAction() {

		if (talk.getId() == 0) {
			createTalk();

		} else {
			updateTalk();

		}

		for (Speaker s : initialSpeakers) {
			try {
				speakerHasTalkService.unassign(s, talk);

			} catch (SpeakerHasTalkNotFoundException e) {

				logger.error("Error unassign Speaker", e);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								e.getMessage()));
			}
		}

		for (Speaker s : speakersDualListModel.getTarget()) {
			try {

				speakerHasTalkService.assign(s, talk);

			} catch (SpeakerNotAvailableException e) {

				logger.error("Error assign Speaker", e);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								e.getMessage()));

			}
		}
		updateModel();
	}

	private void createTalk() {
		try {
			talkService.createTalk(talk);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Talk created"));
		} catch (RoomNotAvailableException e) {
			logger.error("Error updating Talk", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
		} catch (Exception e) {
			logger.error("Error updating Talk", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error creating Talk"));
		}
	}

	private void updateTalk() {
		try {
			talkService.updateTalk(talk);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Talk saved"));
		} catch (RoomNotAvailableException e) {
			logger.error("Error updating Talk", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
		} catch (Exception e) {
			logger.error("Error updating Talk", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error updating Talk"));
		}
	}

	public long getTalkId() {
		return talkId;
	}

	public void setTalkId(long talkId) {
		this.talkId = talkId;
	}

	public Talk getTalk() {
		return talk;
	}

	public void setTalk(Talk talk) {
		this.talk = talk;
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
		if (talk != null) {
			talk.setConference(selectedConference);
		}
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room getSelectedRoom() {
		return selectedRoom;
	}

	public void setSelectedRoom(Room selectedRoom) {
		this.selectedRoom = selectedRoom;
		if (talk != null) {
			talk.setRoom(selectedRoom);
		}
	}

	public DualListModel<Speaker> getSpeakersDualListModel() {
		return speakersDualListModel;
	}

	public void setSpeakersDualListModel(
			DualListModel<Speaker> speakersDualListModel) {
		this.speakersDualListModel = speakersDualListModel;
	}

}
