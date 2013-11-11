package com.prodyna.pac.conference.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;
import org.slf4j.Logger;

import com.prodyna.pac.conference.api.RoomService;
import com.prodyna.pac.conference.model.Room;

@Advanced
@FacesConverter(value = "com.prodyna.pac.conference.RoomConverter")
public class RoomConverter implements Converter {

	@Inject
	private RoomService service;

	@Inject
	private Logger logger;

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
