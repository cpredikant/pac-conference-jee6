/**
 *
 */
package de.predikant.conference.web.rest.unsecure;

import de.predikant.conference.service.api.SpeakerService;
import de.predikant.conference.service.exception.SpeakerNotFoundException;
import de.predikant.conference.service.model.Speaker;
import de.predikant.conference.web.rest.api.unsecure.SpeakerUnsecureRestService;
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
public class SpeakerUnsecureRestServiceImpl implements SpeakerUnsecureRestService {

    @Inject
    private SpeakerService speakerService;

    @Inject
    private Logger logger;

    @Override
    public Response listAllSpeakers() {
        List<Speaker> speakers = null;
        try {
            speakers = speakerService.findAll();

        } catch (Exception e) {
            logger.error("Error listing all Speaker", e);
            throw new InternalServerErrorException(e);
        }
        return Response.ok(speakers).build();
    }

    @Override
    public Response listSpeakerById(String id) {
        Speaker speaker = null;
        try {
            speaker = speakerService.findSpeakerById(Long.valueOf(id));
        } catch (SpeakerNotFoundException e) {
            logger.error("Speaker not Found by id " + id, e);
            throw new NotFoundException(e);
        } catch (Exception e) {
            logger.error("Error find Speaker by id " + id, e);
            throw new InternalServerErrorException(e);
        }
        return Response.ok(speaker).build();
    }

    @Override
    public Response listSpeakersByName(String name) {
        List<Speaker> speakers = null;
        try {
            speakers = speakerService.findSpeakersByName(name);

        } catch (Exception e) {
            logger.error("Error finding Speakers by name " + name, e);
            throw new InternalServerErrorException(e);
        }
        return Response.ok(speakers).build();
    }

}
