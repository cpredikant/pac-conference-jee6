package de.predikant.conference.web.rest.secure;

import de.predikant.conference.service.api.SpeakerHasTalkService;
import de.predikant.conference.service.api.SpeakerService;
import de.predikant.conference.service.api.TalkService;
import de.predikant.conference.service.exception.SpeakerNotFoundException;
import de.predikant.conference.service.exception.TalkNotFoundException;
import de.predikant.conference.service.model.Speaker;
import de.predikant.conference.service.model.Talk;
import de.predikant.conference.web.rest.api.secure.SpeakerHasTalkSecureRestService;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@RequestScoped
public class SpeakerHasTalkSecureRestServiceImpl implements
        SpeakerHasTalkSecureRestService {

    @Inject
    private SpeakerHasTalkService speakerHasTalkService;

    @Inject
    private TalkService talkService;

    @Inject
    private SpeakerService speakerService;

    @Inject
    private Logger logger;

    @Override
    public Response assign(String speakerId, String talkId) {
        try {
            Talk talk = talkService.findTalkById(Long.valueOf(talkId));
            Speaker speaker = speakerService.findSpeakerById(Long
                    .valueOf(speakerId));
            speakerHasTalkService.assign(speaker, talk);
        } catch (TalkNotFoundException e) {
            logger.error("Talk not Found by id " + talkId, e);
            throw new NotFoundException(e);
        } catch (SpeakerNotFoundException e) {
            logger.error("Speaker not Found by id " + speakerId, e);
            throw new NotFoundException(e);
        } catch (Exception e) {
            logger.error("Error assign Speaker and Talk", e);
            throw new InternalServerErrorException(e);
        }

        return Response.ok().build();
    }

    @Override
    public Response unassign(String speakerId, String talkId) {
        try {
            Talk talk = talkService.findTalkById(Long.valueOf(talkId));
            Speaker speaker = speakerService.findSpeakerById(Long
                    .valueOf(speakerId));
            speakerHasTalkService.unassign(speaker, talk);
        } catch (TalkNotFoundException e) {
            logger.error("Talk not Found by id " + talkId, e);
            throw new NotFoundException(e);
        } catch (SpeakerNotFoundException e) {
            logger.error("Speaker not Found by id " + speakerId, e);
            throw new NotFoundException(e);
        } catch (Exception e) {
            logger.error("Error unassign Speaker and Talk", e);
            throw new InternalServerErrorException(e);

        }

        return Response.ok().build();
    }

}
