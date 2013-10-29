package com.prodyna.pac.conference.web.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.prodyna.pac.conference.model.Conference;


public interface ConferenceRESTService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createConference(Conference conference);

//	public abstract Response updateConference(Conference conference);
//
//	public abstract Response deleteConference(Conference conference);
//
//	public abstract Response findConferenceById(long id);
//
//	public abstract Response listConferencesByName(String name);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
    public abstract Response listAll();

	

}