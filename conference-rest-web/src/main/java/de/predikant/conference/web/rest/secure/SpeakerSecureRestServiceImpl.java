/**
 *
 */
package de.predikant.conference.web.rest.secure;

import de.predikant.conference.service.api.SpeakerService;
import de.predikant.conference.service.exception.SpeakerNotFoundException;
import de.predikant.conference.service.model.Speaker;
import de.predikant.conference.web.rest.api.secure.SpeakerSecureRestService;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@RequestScoped
@Timed
public class SpeakerSecureRestServiceImpl implements SpeakerSecureRestService {

    @Inject
    private Logger logger;

    @Inject
    private SpeakerService speakerService;

    @Override
    public Response createSpeaker(Speaker speaker) {
        try {
            speakerService.createSpeaker(speaker);

        } catch (Exception e) {
            logger.error("Error create Speaker " + speaker, e);
            throw new InternalServerErrorException(e);
        }
        return Response.ok(speaker).build();
    }

    @Override
    public Response updateSpeaker(Speaker speaker) {
        Speaker updatedSpeaker = null;
        try {
            updatedSpeaker = speakerService.updateSpeaker(speaker);

        } catch (SpeakerNotFoundException e) {
            logger.error("Speaker not found for update " + speaker, e);
            throw new NotFoundException(e);
        } catch (Exception e) {
            logger.error("Error updating Speaker " + speaker, e);
            throw new NotFoundException(e);
        }

        return Response.ok(updatedSpeaker).build();
    }

    @Override
    public Response deleteSpeaker(String id) {
        try {
            speakerService.deleteSpeaker(Long.valueOf(id));
        } catch (SpeakerNotFoundException e) {
            logger.error("Speaker not found for delete " + id, e);
            throw new NotFoundException(e);
        } catch (Exception e) {
            logger.error("Error deleting Speaker " + id, e);
            throw new NotFoundException(e);
        }

        return Response.ok().build();
    }

}
