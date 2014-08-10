package de.predikant.conference.web.controller;

import java.io.Serializable;
import java.util.List;



import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import de.predikant.conference.service.api.ConferenceService;
import de.predikant.conference.service.api.TalkService;
import de.predikant.conference.service.exception.ConferenceNotFoundException;
import de.predikant.conference.service.model.Conference;
import de.predikant.conference.service.model.Talk;

@Named
@ViewScoped
public class ConferenceController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private long conferenceId;

	private Conference conference;

	private List<Talk> talks;

	@Inject
	private ConferenceService conferenceService;

	@Inject
	private TalkService talkService;
	
	
	public void initViewParams(){
		conference = loadConference(conferenceId);
		talks = talkService.findTalksByConferenceId(conferenceId);
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

}
