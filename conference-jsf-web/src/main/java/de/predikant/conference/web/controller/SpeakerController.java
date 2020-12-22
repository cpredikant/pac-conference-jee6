package de.predikant.conference.web.controller;

import de.predikant.conference.service.api.SpeakerHasTalkService;
import de.predikant.conference.service.api.SpeakerService;
import de.predikant.conference.service.exception.SpeakerNotFoundException;
import de.predikant.conference.service.model.Speaker;
import de.predikant.conference.service.model.Talk;
import org.slf4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class SpeakerController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger logger;

    @Inject
    private SpeakerService speakerService;

    @Inject
    private SpeakerHasTalkService speakerHasTalkService;

    private long speakerId;

    private Speaker speaker;

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
