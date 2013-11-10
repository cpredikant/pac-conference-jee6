package com.prodyna.pac.conference.web.controller.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.prodyna.pac.conference.api.TalkService;
import com.prodyna.pac.conference.exception.TalkNotFoundException;
import com.prodyna.pac.conference.model.Talk;

@Named
@ViewScoped
public class EditTalkController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private long talkId;

	private Talk talk;

	@Inject
	private TalkService talkService;

	@PostConstruct
	public void init() {
		if (talk == null) {
			talk = new Talk();
		}
	}

	public void initViewParams() {
		if (talkId != 0) {
			talk = loadTalk(talkId);
		}

	}

	private Talk loadTalk(long id) {
		Talk t = null;

		try {
			t = talkService.findTalkById(id);
		} catch (TalkNotFoundException e) {
			logger.error("Talk with id {} not Found", id);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
							"Talk not Found"));
		}

		return t;
	}

	public void saveTalkAction() {
		if(talk.getId() == 0){
			createTalk();
		} else {
			updateTalk();
		}
	}
	
	private void createTalk(){
		try {
			talkService.createTalk(talk);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Talk created"));
		} catch (Exception e) {
			logger.error("Error updating Speaker {}", talk, e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error creating Talk"));
		}
	}
	
	private void updateTalk(){
		try {
			talkService.updateTalk(talk);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
							"Talk saved"));
		} catch (Exception e) {
			logger.error("Error updating Speaker {}", talk, e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error updating Talk"));
		}
	}

	public long getTalkId() {
		return talkId;
	}

	public void setTalkId(long talkId) {
		this.talkId = talkId;
	}

	public Talk getTalk() {
		return talk;
	}

	public void setTalk(Talk talk) {
		this.talk = talk;
	}

}
