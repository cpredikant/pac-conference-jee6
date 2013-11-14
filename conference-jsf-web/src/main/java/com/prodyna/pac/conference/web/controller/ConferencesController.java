package com.prodyna.pac.conference.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.prodyna.pac.conference.service.api.ConferenceService;
import com.prodyna.pac.conference.service.model.Conference;

@Named
@ViewScoped
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
