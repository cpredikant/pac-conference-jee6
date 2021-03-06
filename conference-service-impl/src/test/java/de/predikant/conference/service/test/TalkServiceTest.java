package de.predikant.conference.service.test;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.slf4j.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.predikant.conference.service.api.ConferenceService;
import de.predikant.conference.service.api.RoomService;
import de.predikant.conference.service.api.TalkService;
import de.predikant.conference.service.model.Conference;
import de.predikant.conference.service.model.Room;
import de.predikant.conference.service.model.Talk;

@RunWith(Arquillian.class)
public class TalkServiceTest {

	@Inject
	Logger logger;

	@Inject
	private RoomService roomService;

	@Inject
	private ConferenceService conferenceService;

	@Inject
	private TalkService talkService;
	
	@Inject
	private SimpleDateFormat sdf;
	
	@Deployment
	public static Archive<?> createTestArchive() {

		WebArchive war = ShrinkWrap.create(WebArchive.class,
				"TalkrServiceTest.war");
		war.addPackages(true, "de.predikant.conference");
		war.addAsResource("META-INF/test-beans.xml", "META-INF/beans.xml");
		war.addAsResource("META-INF/test-persistence.xml",
				"META-INF/persistence.xml");
		return war;
	}

	@Test
	@InSequence(1)
	public void createTalkTest() throws Exception {
		
		Conference c = new Conference();
		c.setName("Conference");
		c.setDescription("Super Conference");
		c.setStart(sdf.parse("01.01.2015 12:00:00"));
		c.setEnd(sdf.parse("10.01.2015 12:00:00"));
		
		conferenceService.createConference(c);
		
		Room r = new Room();
		r.setName("Room");
		r.setCapacity(10);
		r.setConference(c);
		
		roomService.createRoom(r);
		
		Talk talk = new Talk();
		talk.setDescription("Description");
		talk.setDuration(60);
		talk.setName("Talk");
		talk.setStart(sdf.parse("01.01.2015 13:00:00"));
		talk.setConference(c);
		talk.setRoom(r);

		talkService.createTalk(talk);

		Assert.assertTrue(talk.getId() > 0);
	}

	@Test
	@InSequence(2)
	public void updateTalkTest() throws Exception {
		Conference c = new Conference();
		c.setName("Conference");
		c.setDescription("Super Conference");
		c.setStart(sdf.parse("01.01.2015 12:00:00"));
		c.setEnd(sdf.parse("10.01.2015 12:00:00"));
		
		conferenceService.createConference(c);
		
		Room r = new Room();
		r.setName("Room");
		r.setCapacity(10);
		r.setConference(c);
		
		roomService.createRoom(r);
		
		Talk talk = new Talk();
		talk.setDescription("Description");
		talk.setDuration(60);
		talk.setName("Talk");
		talk.setStart(sdf.parse("01.01.2015 13:00:00"));
		talk.setConference(c);
		talk.setRoom(r);

		talkService.createTalk(talk);

		Assert.assertTrue(talk.getId() > 0);

		talk.setName("Updated Talk");

		Talk updatedTalk = talkService.updateTalk(talk);

		Assert.assertEquals("Updated Talk", updatedTalk.getName());
	}

	@Test()
	@InSequence(3)
	public void deleteTalkTest() throws Exception{
		Conference c = new Conference();
		c.setName("Conference");
		c.setDescription("Super Conference");
		c.setStart(sdf.parse("01.01.2015 12:00:00"));
		c.setEnd(sdf.parse("10.01.2015 12:00:00"));
		
		conferenceService.createConference(c);
		
		Room r = new Room();
		r.setName("Room");
		r.setCapacity(10);
		r.setConference(c);
		
		roomService.createRoom(r);
		
		Talk talk = new Talk();
		talk.setDescription("Description");
		talk.setDuration(60);
		talk.setName("Talk");
		talk.setStart(sdf.parse("01.01.2015 13:00:00"));
		talk.setConference(c);
		talk.setRoom(r);

		talkService.createTalk(talk);

		Assert.assertTrue(talk.getId() > 0);

		talkService.deleteTalk(talk.getId());

		//talkService.findTalkById(talk.getId());

	}

	@Test
	@InSequence(4)
	public void findTalkByIdTest() throws Exception {
		Conference c = new Conference();
		c.setName("Conference");
		c.setDescription("Super Conference");
		c.setStart(sdf.parse("01.01.2015 12:00:00"));
		c.setEnd(sdf.parse("10.01.2015 12:00:00"));
		
		conferenceService.createConference(c);
		
		Room r = new Room();
		r.setName("Room");
		r.setCapacity(10);
		r.setConference(c);
		
		roomService.createRoom(r);
		
		Talk talk = new Talk();
		talk.setDescription("Description");
		talk.setDuration(60);
		talk.setName("Talk");
		talk.setStart(sdf.parse("01.01.2015 13:00:00"));
		talk.setConference(c);
		talk.setRoom(r);

		talkService.createTalk(talk);

		Assert.assertTrue(talk.getId() > 0);

		Talk foundTalk = talkService.findTalkById(talk.getId());

		Assert.assertEquals(talk.getId(), foundTalk.getId());
	}

	@Test
	@InSequence(5)
	public void findTalksByNameTest() throws Exception {
		
		Conference c = new Conference();
		c.setName("Conference");
		c.setDescription("Super Conference");
		c.setStart(sdf.parse("01.01.2015 12:00:00"));
		c.setEnd(sdf.parse("10.01.2015 12:00:00"));
		
		conferenceService.createConference(c);
		
		Room r = new Room();
		r.setName("Room");
		r.setCapacity(10);
		r.setConference(c);
		
		roomService.createRoom(r);
		
		
		Talk t1 = new Talk();
		t1.setDescription("Description");
		t1.setDuration(60);
		t1.setName("Talk");
		t1.setStart(sdf.parse("01.01.2015 13:00:00"));
		t1.setConference(c);
		t1.setRoom(r);

		talkService.createTalk(t1);

		Talk t2 = new Talk();
		t2.setDescription("Description");
		t2.setDuration(60);
		t2.setName("Talk");
		t2.setStart(sdf.parse("01.01.2015 14:00:00"));
		t2.setConference(c);
		t2.setRoom(r);

		talkService.createTalk(t2);

		List<Talk> foundTalks = talkService.findTalksByName("Talk");

		Assert.assertTrue(foundTalks.size() > 0);

		Assert.assertEquals("Talk", foundTalks.get(0).getName());
	}

	@Test
	@InSequence(6)
	public void findTalksByConferenceIdTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("Desription");
		conference.setName("Conference");
		conference.setStart(sdf.parse("01.01.2015 12:00:00"));
		conference.setEnd(sdf.parse("10.01.2015 12:00:00"));
		
		conferenceService.createConference(conference);
		
		Room r = new Room();
		r.setName("Room");
		r.setCapacity(10);
		r.setConference(conference);
		
		roomService.createRoom(r);
		
		Talk talk = new Talk();
		talk.setDescription("Description");
		talk.setDuration(60);
		talk.setName("Talk");
		talk.setConference(conference);
		talk.setRoom(r);
		talk.setStart(sdf.parse("01.01.2015 13:00:00"));
		
		talkService.createTalk(talk);
		
		List<Talk> foundTalks = talkService.findTalksByConferenceId(conference.getId());
		
		Assert.assertTrue(foundTalks.size() > 0 );
		
		Assert.assertEquals(talk.getId(), foundTalks.get(0).getId());
	}

	@Test
	@InSequence(7)
	public void findTalksByRoomIdTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("Desription");
		conference.setName("Conference");
		conference.setStart(sdf.parse("01.01.2015 12:00:00"));
		conference.setEnd(sdf.parse("10.01.2015 12:00:00"));
		
		conferenceService.createConference(conference);
		
		
		Room room = new Room();
		room.setCapacity(10);
		room.setName("Room");
		room.setConference(conference);

		
		roomService.createRoom(room);
		
		
		Talk talk = new Talk();
		talk.setDescription("Description");
		talk.setDuration(60);
		talk.setName("Talk");
		talk.setRoom(room);
		talk.setConference(conference);
		talk.setStart(sdf.parse("01.01.2015 13:00:00"));
		
		talkService.createTalk(talk);
		
		List<Talk> foundTalks = talkService.findTalksByRoomId(room.getId());
		
		Assert.assertTrue(foundTalks.size() > 0 );
		
		Assert.assertEquals(talk.getId(), foundTalks.get(0).getId());
	}

	@Test
	@InSequence(8)
	public void findAllTest() throws Exception {
		Conference c = new Conference();
		c.setName("Conference");
		c.setDescription("Super Conference");
		c.setStart(sdf.parse("01.01.2015 12:00:00"));
		c.setEnd(sdf.parse("10.01.2015 12:00:00"));
		
		conferenceService.createConference(c);
		
		Room r = new Room();
		r.setName("Room");
		r.setCapacity(10);
		r.setConference(c);
		
		roomService.createRoom(r);
		
		
		Talk t1 = new Talk();
		t1.setDescription("Description");
		t1.setDuration(60);
		t1.setName("Talk");
		t1.setStart(sdf.parse("01.01.2015 13:00:00"));
		t1.setConference(c);
		t1.setRoom(r);
		talkService.createTalk(t1);

		Talk t2 = new Talk();
		t2.setDescription("Description");
		t2.setDuration(60);
		t2.setName("Talk");
		t2.setConference(c);
		t2.setRoom(r);
		t2.setStart(sdf.parse("01.01.2015 14:00:00"));

		talkService.createTalk(t2);

		List<Talk> foundTalks = talkService.findAll();

		Assert.assertTrue(foundTalks.size() > 0);

	}

}
