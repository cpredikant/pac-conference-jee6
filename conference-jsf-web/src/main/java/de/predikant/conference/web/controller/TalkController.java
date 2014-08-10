package de.predikant.conference.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import de.predikant.conference.service.api.SpeakerHasTalkService;
import de.predikant.conference.service.api.TalkService;
import de.predikant.conference.service.exception.TalkNotFoundException;
import de.predikant.conference.service.model.Speaker;
import de.predikant.conference.service.model.Talk;

@Named
@ViewScoped
public class TalkController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private long talkId;

	private Talk talk;

	@Inject
	private TalkService talkService;

	private List<Speaker> speakers;

	@Inject
	private SpeakerHasTalkService speakerHasTalkService;

	public void initViewParams() {
		talk = loadTalk(talkId);
		speakers = loadSpeakers(talkId);
	}

	private List<Speaker> loadSpeakers(long id) {

		return speakerHasTalkService.findSpeakersByTalk(talk);
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

	@PostConstruct
	public void init() {

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

	public List<Speaker> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}

}
