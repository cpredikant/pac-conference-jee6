package com.prodyna.pac.conference.service.test;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.prodyna.pac.conference.client.api.ConferenceService;
import com.prodyna.pac.conference.client.api.RoomService;
import com.prodyna.pac.conference.client.api.SpeakerHasTalkService;
import com.prodyna.pac.conference.client.api.SpeakerService;
import com.prodyna.pac.conference.client.api.TalkService;
import com.prodyna.pac.conference.client.model.Conference;
import com.prodyna.pac.conference.client.model.Room;
import com.prodyna.pac.conference.client.model.Speaker;
import com.prodyna.pac.conference.client.model.SpeakerHasTalk;
import com.prodyna.pac.conference.client.model.Talk;

@RunWith(Arquillian.class)
public class SpeakerHasTalkServiceTest {

	@Inject
	Logger logger;

	@Inject
	private SpeakerService speakerService;
	
	@Inject
	private TalkService talkService;
	
	@Inject
	private SpeakerHasTalkService speakerHasTalkService; 
	
	@Inject
	private Date dateInFuture;

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
		Speaker s = new Speaker();
		s.setName("Speaker");
		s.setDescription("Speaker Description");
		
		speakerService.createSpeaker(s);
		
		Talk t = new Talk();
		t.setName("Talk");
		t.setDescription("Talk Description");
		t.setDuration(10);
		t.setStart(dateInFuture);
		
		talkService.createTalk(t);
		
		speakerHasTalkService.assign(s, t);
		
		SpeakerHasTalk hasTalk = speakerHasTalkService.findSpeakerHasTalkBySpeakerAndTalk(s, t);
		
		Assert.assertNotNull(hasTalk);
		

	}

	@Test
	@InSequence(2)
	public void unassignTest() throws Exception {
		

	}

	
}
