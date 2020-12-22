package de.predikant.conference.web.converter;

import de.predikant.conference.service.api.ConferenceService;
import de.predikant.conference.service.model.Conference;
import org.slf4j.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


@FacesConverter(value = "de.predikant.conference.ConferenceConverter")
public class ConferenceConverter implements Converter {

    @Inject
    private Logger logger;

    @Inject
    private ConferenceService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String value) {

        Conference conference = null;

        long id = Long.valueOf(value);

        if (id != 0) {
            try {
                conference = service.findConferenceById(id);
            } catch (Exception e) {
                logger.error("Error converting Conference", e);
            }
        }

        return conference;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object value) {

        String id = "";

        if (value != null && value instanceof Conference) {
            Conference c = (Conference) value;
            id = String.valueOf(c.getId());
        }

        return id;

    }
}
