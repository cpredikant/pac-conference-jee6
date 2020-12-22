package de.predikant.conference.web.converter;

import de.predikant.conference.service.api.RoomService;
import de.predikant.conference.service.model.Room;
import org.slf4j.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


@FacesConverter(value = "de.predikant.conference.RoomConverter")
public class RoomConverter implements Converter {

    @Inject
    private Logger logger;

    @Inject
    private RoomService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String value) {

        Room room = null;

        long id = Long.valueOf(value);

        if (id != 0) {
            try {
                room = service.findRoomById(id);
            } catch (Exception e) {
                logger.error("Error converting Room", e);
            }
        }

        return room;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object value) {

        String id = "";

        if (value != null && value instanceof Room) {
            Room r = (Room) value;
            id = String.valueOf(r.getId());
        }

        return id;

    }
}
