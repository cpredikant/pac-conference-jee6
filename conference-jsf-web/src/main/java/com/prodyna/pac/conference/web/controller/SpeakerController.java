package com.prodyna.pac.conference.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.prodyna.pac.conference.api.SpeakerHasTalkService;
import com.prodyna.pac.conference.api.SpeakerService;
import com.prodyna.pac.conference.exception.SpeakerNotFoundException;
import com.prodyna.pac.conference.model.Speaker;
import com.prodyna.pac.conference.model.Talk;

@Named
@ViewScoped
public class SpeakerController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private long speakerId;

	private Speaker speaker;

	@Inject
	private SpeakerService speakerService;

	@Inject
	private SpeakerHasTalkService speakerHasTalkService;

	private List<Talk> talks;

	public void initViewParams() {
		speaker = loadSpeaker(speakerId);
		talks = loadTalks(speaker);
	}

	private List<Talk> loadTalks(Speaker s) {
		return speakerHasTalkService.findTalksBySpeaker(s);
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

	public List<Talk> getTalks() {
		return talks;
	}

	public void setTalks(List<Talk> talks) {
		this.talks = talks;
	}

}
