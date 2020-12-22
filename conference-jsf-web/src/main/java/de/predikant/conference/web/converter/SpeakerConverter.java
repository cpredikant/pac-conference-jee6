package de.predikant.conference.web.converter;

import de.predikant.conference.service.api.SpeakerService;
import de.predikant.conference.service.model.Speaker;
import org.slf4j.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


@FacesConverter(value = "de.predikant.conference.SpeakerConverter")
public class SpeakerConverter implements Converter {

    @Inject
    private Logger logger;

    @Inject
    private SpeakerService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String value) {

        Speaker speaker = null;

        long id = Long.valueOf(value);

        if (id != 0) {
            try {
                speaker = service.findSpeakerById(id);
            } catch (Exception e) {
                logger.error("Error converting Speaker", e);
            }
        }

        return speaker;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object value) {

        String id = "";

        if (value != null && value instanceof Speaker) {
            Speaker s = (Speaker) value;
            id = String.valueOf(s.getId());
        }

        return id;

    }
}
