package de.predikant.conference.web.controller.admin;

import de.predikant.conference.service.api.SpeakerService;
import de.predikant.conference.service.exception.SpeakerNotFoundException;
import de.predikant.conference.service.model.Speaker;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

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
			logger.error("Speaker with id {} not Found", id);
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
