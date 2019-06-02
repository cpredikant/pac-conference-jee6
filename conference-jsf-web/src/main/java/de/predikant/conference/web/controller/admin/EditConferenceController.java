package de.predikant.conference.web.controller.admin;

import de.predikant.conference.service.api.ConferenceService;
import de.predikant.conference.service.exception.ConferenceNotFoundException;
import de.predikant.conference.service.model.Conference;
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
public class EditConferenceController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger logger;

    private long conferenceId;

    private Conference conference;

    @Inject
    private ConferenceService conferenceService;


    public void initViewParams() {
        if (conferenceId != 0) {
            conference = loadConference(conferenceId);
        }
    }

    @PostConstruct
    public void init() {
        if (conference == null) {
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
        createConfrence();
    }

    private void createConfrence() {
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

    private void updateConference() {
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


}
