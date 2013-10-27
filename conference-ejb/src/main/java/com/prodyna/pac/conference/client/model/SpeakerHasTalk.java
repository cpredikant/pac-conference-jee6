package com.prodyna.pac.conference.client.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="speaker_has_talk")
@NamedQueries({
	@NamedQuery(name = "SpeakerHasTalk.findSpeakerByTalk", query = "SELECT s FROM Speaker s, SpeakerHasTalk sht WHERE s = sht.speaker AND sht.talk = :talk"),
	@NamedQuery(name = "SpeakerHasTalk.findTalksBySpeakerId", query = "SELECT t FROM Talk t, SpeakerHasTalk sht WHERE t = sht.talk AND sht.speaker = :speaker" ),
	@NamedQuery(name = "SpeakerHasTalk.findSpeakerHasTalkBySpeakerAndTalk", query = "SELECT sht FROM SpeakerHasTalk sht WHERE sht.speaker = :speaker AND sht.talk = :talk" ),
})
public class SpeakerHasTalk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "speaker_id")
	private Speaker speaker;
	
	@ManyToOne
	@JoinColumn(name = "talk_id")
	private Talk talk;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public Talk getTalk() {
		return talk;
	}

	public void setTalk(Talk talk) {
		this.talk = talk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((speaker == null) ? 0 : speaker.hashCode());
		result = prime * result + ((talk == null) ? 0 : talk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpeakerHasTalk other = (SpeakerHasTalk) obj;
		if (id != other.id)
			return false;
		if (speaker == null) {
			if (other.speaker != null)
				return false;
		} else if (!speaker.equals(other.speaker))
			return false;
		if (talk == null) {
			if (other.talk != null)
				return false;
		} else if (!talk.equals(other.talk))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SpeakerHasTalk [id=" + id + ", speaker=" + speaker + ", talk="
				+ talk + "]";
	}
	

}
