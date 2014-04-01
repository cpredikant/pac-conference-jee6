package com.prodyna.pac.conference.web.controller.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;



import org.jboss.logging.Logger;

import com.prodyna.pac.conference.service.api.SpeakerService;
import com.prodyna.pac.conference.service.exception.SpeakerNotFoundException;
import com.prodyna.pac.conference.service.model.Speaker;

@Named
@ViewScoped
public class EditSpeakerController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private long speakerId;

	private Speaker speaker;

	@Inject
	private SpeakerService speakerService;

	public void initViewParams() {
		if (speakerId != 0) {
			speaker = loadSpeaker(speakerId);
		}

	}
	
	@PostConstruct
	public void init(){
		if(speaker == null){
			speaker = new Speaker();
		}
	}

	private Speaker loadSpeaker(long id) {
		Speaker s = null;

		try {
			s = speakerService.findSpeakerById(id);
		} catch (SpeakerNotFoundException e) {
			logger.errorf("Speaker with id {} not Found", id);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
							"Speaker not Found"));
		}

		return s;
	}

	public void saveSpeakerAction() {
		if (speaker.getId() == 0){
			createSpeaker();
		} else {
			updateSpeaker();
		}
	}
	
	private void createSpeaker(){
		try {
			speakerService.createSpeaker(speaker);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Speaker created"));
		} catch (Exception e) {
			logger.error("Error updating Speaker {}", speaker, e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error creating Speaker"));
		}
	}
	
	private void updateSpeaker(){
		try {
			speakerService.updateSpeaker(speaker);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Speaker saved"));
		} catch (Exception e) {
			logger.error("Error updating Speaker {}", speaker, e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error updating Speaker"));
		}
	}

	public long getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(long speakerId) {
		this.speakerId = speakerId;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

}
