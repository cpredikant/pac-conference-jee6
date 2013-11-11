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
import com.prodyna.pac.conference.api.SpeakerService;
import com.prodyna.pac.conference.model.Conference;
import com.prodyna.pac.conference.model.Speaker;

@Named
@ViewScoped
public class AdminController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private List<Conference> conferences;

	private List<Speaker> speakers;

	@Inject
	private ConferenceService conferenceService;

	@Inject
	private SpeakerService speakerService;

	@PostConstruct
	public void init() {
		conferences = conferenceService.findAll();
		speakers = speakerService.findAll();
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
	
	public void deleteConference(long id){
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
	}
	
	public void deleteSpeaker(long id){
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
	}

}
