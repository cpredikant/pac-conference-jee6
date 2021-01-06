/**
 *
 */
package de.predikant.conference.web.rest.unsecure;

import de.predikant.conference.service.api.ConferenceService;
import de.predikant.conference.service.exception.ConferenceNotFoundException;
import de.predikant.conference.service.model.Conference;
import de.predikant.conference.web.rest.api.unsecure.ConferenceUnsecureRestService;
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
public class ConferenceUnsecureRestServiceImpl implements
        ConferenceUnsecureRestService {

    @Inject
    private ConferenceService conferenceService;

    @Inject
    private Logger logger;

    @Override
    public Response listAllConferences() {
        List<Conference> conferences = null;
        try {
            conferences = conferenceService.findAll();

        } catch (Exception e) {
            logger.error("Error listing all Conferences", e);
            throw new InternalServerErrorException(e);
        }
        return Response.ok(conferences).build();
    }

    @Override
    public Response listConferenceById(String id) {
        Conference conference = null;
        try {
            conference = conferenceService.findConferenceById(Long.valueOf(id));
        } catch (ConferenceNotFoundException e) {
            logger.error("Conference not Found by id " + id, e);
            throw new NotFoundException(e);
        } catch (Exception e) {
            logger.error("Error find Conferences by id " + id, e);
            throw new InternalServerErrorException(e);
        }
        return Response.ok(conference).build();
    }

    @Override
    public Response listConferencesByName(String name) {
        List<Conference> conferences = null;
        try {
            conferences = conferenceService.findConferenceByName(name);

        } catch (Exception e) {
            logger.error("Error find Conferences by name " + name, e);
            throw new InternalServerErrorException(e);
        }
        return Response.ok(conferences).build();
    }

}
