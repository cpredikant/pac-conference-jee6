package com.prodyna.pac.conference.service.test;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.pac.conference.service.api.ConferenceService;
import com.prodyna.pac.conference.service.api.RoomService;
import com.prodyna.pac.conference.service.api.SpeakerHasTalkService;
import com.prodyna.pac.conference.service.api.SpeakerService;
import com.prodyna.pac.conference.service.api.TalkService;
import com.prodyna.pac.conference.service.exception.SpeakerHasTalkNotFoundException;
import com.prodyna.pac.conference.service.model.Conference;
import com.prodyna.pac.conference.service.model.Room;
import com.prodyna.pac.conference.service.model.Speaker;
import com.prodyna.pac.conference.service.model.SpeakerHasTalk;
import com.prodyna.pac.conference.service.model.Talk;

@RunWith(Arquillian.class)
public class SpeakerHasTalkServiceTest {

	@Inject
	Logger logger;

	@Inject
	private SpeakerService speakerService;
	
	@Inject
	private TalkService talkService;
	
	@Inject
	private ConferenceService conferenceService;
	
	@Inject
	private RoomService roomService;
	
	@Inject
	private SpeakerHasTalkService speakerHasTalkService; 
	
	@Inject
	private SimpleDateFormat sdf;

	@Deployment
	public static Archive<?> createTestArchive() {

		WebArchive war = ShrinkWrap.create(WebArchive.class,
				"SpeakerHAsTalkServiceTest.war");
		war.addPackages(true, "com.prodyna.pac.conference");
		war.addAsResource("META-INF/test-beans.xml", "META-INF/beans.xml");
		war.addAsResource("META-INF/test-persistence.xml",
				"META-INF/persistence.xml");
		return war;
	}

	@Test
	@InSequence(1)
	public void assingTest() throws Exception {
		Conference c = new Conference();
		c.setDescription("A description");
		c.setName("findyByname");
		c.setStart(sdf.parse("01.01.2015 12:00:00"));
		c.setEnd(sdf.parse("10.01.2015 12:00:00"));

		conferenceService.createConference(c);
		
		
		Speaker s = new Speaker();
		s.setName("Speaker");
		s.setDescription("Speaker Description");
		
		speakerService.createSpeaker(s);
		
		Room r = new Room();
		r.setCapacity(1000);
		r.setName("Audimax2");
		r.setConference(c);

		roomService.createRoom(r);
		
		Talk t = new Talk();
		t.setName("Talk");
		t.setDescription("Talk Description");
		t.setDuration(10);
		t.setStart(sdf.parse("01.01.2015 13:00:00"));
		t.setConference(c);
		t.setRoom(r);
		
		talkService.createTalk(t);
		
		speakerHasTalkService.assign(s, t);
		
		SpeakerHasTalk hasTalk = speakerHasTalkService.findSpeakerHasTalkBySpeakerAndTalk(s, t);
		
		Assert.assertNotNull(hasTalk);
		

	}

	@Test(expected=SpeakerHasTalkNotFoundException.class)
	@InSequence(2)
	public void unassignTest() throws Exception {
		
		Conference c = new Conference();
		c.setDescription("A description");
		c.setName("findyByname");
		c.setStart(sdf.parse("01.01.2015 12:00:00"));
		c.setEnd(sdf.parse("10.01.2015 12:00:00"));

		conferenceService.createConference(c);
		
		
		Speaker s = new Speaker();
		s.setName("Speaker");
		s.setDescription("Speaker Description");
		
		speakerService.createSpeaker(s);
		
		Room r = new Room();
		r.setCapacity(1000);
		r.setName("Audimax2");
		r.setConference(c);

		roomService.createRoom(r);
		
		Talk t = new Talk();
		t.setName("Talk");
		t.setDescription("Talk Description");
		t.setDuration(10);
		t.setStart(sdf.parse("01.01.2015 13:00:00"));
		t.setConference(c);
		t.setRoom(r);
		
		talkService.createTalk(t);
		
		speakerHasTalkService.assign(s, t);
		
		SpeakerHasTalk hasTalk = speakerHasTalkService.findSpeakerHasTalkBySpeakerAndTalk(s, t);
		
		Assert.assertNotNull(hasTalk);
		
		speakerHasTalkService.unassign(s, t);
		
		speakerHasTalkService.findSpeakerHasTalkBySpeakerAndTalk(s, t);
			
	}

	
}
