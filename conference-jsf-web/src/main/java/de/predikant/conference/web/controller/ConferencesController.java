package de.predikant.conference.web.controller;

import de.predikant.conference.service.api.ConferenceService;
import de.predikant.conference.service.model.Conference;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ConferencesController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ConferenceService conferenceService;

    private List<Conference> conferences;

    @PostConstruct
    public void init() {
        conferences = conferenceService.findAll();
    }

    public List<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
    }

}
