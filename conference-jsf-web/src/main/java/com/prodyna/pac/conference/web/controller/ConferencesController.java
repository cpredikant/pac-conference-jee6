package com.prodyna.pac.conference.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.prodyna.pac.conference.api.ConferenceService;
import com.prodyna.pac.conference.model.Conference;

@Model
public class ConferencesController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConferenceService conferenceService;

	private List<Conference> conferences;

	@PostConstruct
	public void init() {
		conferences = conferenceService.findAll();
	}

	public List<Conference> getConferences() {
		return conferences;
	}

	public void setConferences(List<Conference> conferences) {
		this.conferences = conferences;
	}

}
