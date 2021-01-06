package de.predikant.conference.web.rest.unsecure;

import de.predikant.conference.service.api.SpeakerHasTalkService;
import de.predikant.conference.service.api.SpeakerService;
import de.predikant.conference.service.api.TalkService;
import de.predikant.conference.service.exception.SpeakerHasTalkNotFoundException;
import de.predikant.conference.service.exception.SpeakerNotFoundException;
import de.predikant.conference.service.exception.TalkNotFoundException;
import de.predikant.conference.service.model.Speaker;
import de.predikant.conference.service.model.SpeakerHasTalk;
import de.predikant.conference.service.model.Talk;
import de.predikant.conference.web.rest.api.unsecure.SpeakerHasTalkUnsecureRestService;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Timed
public class SpeakerHasTalkUnsecureRestServiceImpl implements
        SpeakerHasTalkUnsecureRestService {

    @Inject
    private SpeakerHasTalkService speakerHasTalkService;

    @Inject
    private TalkService talkService;

    @Inject
    private SpeakerService speakerService;

    @Inject
    private Logger logger;

    @Override
    public Response findSpeakerHasTalkBySpeakerAndTalk(String speakerId,
                                                       String talkId) {
        SpeakerHasTalk speakerHasTalk = null;
        try {
            Talk talk = talkService.findTalkById(Long.valueOf(talkId));
            Speaker speaker = speakerService.findSpeakerById(Long
                    .valueOf(speakerId));
            speakerHasTalk = speakerHasTalkService
                    .findSpeakerHasTalkBySpeakerAndTalk(speaker, talk);
        } catch (SpeakerHasTalkNotFoundException e) {
            logger.error("SpeakerHasTalk not found", e);
            throw new NotFoundException(e);
        } catch (TalkNotFoundException e) {
            logger.error("Talk not Found by id " + talkId, e);
            throw new NotFoundException(e);
        } catch (SpeakerNotFoundException e) {
            logger.error("Speaker not Found by id " + speakerId, e);
            throw new NotFoundException(e);
        } catch (Exception e) {
            logger.error("Error list Speakers by Talk", e);
            throw new InternalServerErrorException(e);

        }

        return Response.ok(speakerHasTalk).build();

    }

    @Override
    public Response listSpeakersByTalk(String talkId) {
        List<Speaker> speakers = null;
        try {
            Talk talk = talkService.findTalkById(Long.valueOf(talkId));
            speakers = speakerHasTalkService.findSpeakersByTalk(talk);

        } catch (TalkNotFoundException e) {
            logger.error("Talk not Found by id " + talkId, e);
            throw new NotFoundException(e);
        } catch (Exception e) {
            logger.error("Error list Speakers by Talk", e);
            throw new InternalServerErrorException(e);
        }
        return Response.ok(speakers).build();
    }

    @Override
    public Response listTalksBySpeaker(String speakerId) {
        List<Talk> talks = null;
        try {
            Speaker speaker = speakerService.findSpeakerById(Long
                    .valueOf(speakerId));
            talks = speakerHasTalkService.findTalksBySpeaker(speaker);

        } catch (SpeakerNotFoundException e) {
            logger.error("Speaker not Found by id " + speakerId, e);
            throw new NotFoundException(e);
        } catch (Exception e) {
            logger.error("Error list Talks by Speaker", e);
            throw new InternalServerErrorException(e);
        }
        return Response.ok(talks).build();
    }

}
