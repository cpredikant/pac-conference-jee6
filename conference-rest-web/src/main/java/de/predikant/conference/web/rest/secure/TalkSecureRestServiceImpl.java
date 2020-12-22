/**
 *
 */
package de.predikant.conference.web.rest.secure;

import de.predikant.conference.service.api.TalkService;
import de.predikant.conference.service.exception.TalkNotFoundException;
import de.predikant.conference.service.model.Talk;
import de.predikant.conference.web.rest.api.secure.TalkSecureRestService;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@RequestScoped
public class TalkSecureRestServiceImpl implements TalkSecureRestService {

    @Inject
    private Logger logger;

    @Inject
    private TalkService talkService;

    @Override
    public Response createTalk(Talk talk) {
        try {
            talkService.createTalk(talk);

        } catch (Exception e) {
            logger.error("Error create Talk " + talk, e);
            throw new InternalServerErrorException(e);
        }
        return Response.ok(talk).build();
    }

    @Override
    public Response updateTalk(Talk talk) {
        Talk updatedTalk = null;
        try {
            updatedTalk = talkService.updateTalk(talk);

        } catch (TalkNotFoundException e) {
            logger.error("Talk not found for update " + talk, e);
            throw new NotFoundException(e);
        } catch (Exception e) {
            logger.error("Error updating Talk " + talk, e);
            throw new NotFoundException(e);
        }

        return Response.ok(updatedTalk).build();
    }


    @Override
    public Response deleteTalk(String id) {
        try {
            talkService.deleteTalk(Long.valueOf(id));
        } catch (TalkNotFoundException e) {
            logger.error("Talk not found for delete " + id, e);
            throw new NotFoundException(e);
        } catch (Exception e) {
            logger.error("Error deleting Talk " + id, e);
            throw new NotFoundException(e);
        }

        return Response.ok().build();
    }

}
