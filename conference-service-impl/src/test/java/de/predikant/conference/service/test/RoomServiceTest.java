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
import de.predikant.conference.service.exception.RoomNotFoundException;
import de.predikant.conference.service.model.Conference;
import de.predikant.conference.service.model.Room;

@RunWith(Arquillian.class)
public class RoomServiceTest {

	@Inject
	Logger logger;

	@Inject
	private RoomService roomService;

	@Inject
	private ConferenceService conferenceService;
	
	@Inject
	private SimpleDateFormat sdf;

	@Deployment
	public static Archive<?> createTestArchive() {

		WebArchive war = ShrinkWrap.create(WebArchive.class,
				"RoomServiceTest.war");
		war.addPackages(true, "de.predikant.conference");
		war.addAsResource("META-INF/test-beans.xml", "META-INF/beans.xml");
		war.addAsResource("META-INF/test-persistence.xml",
				"META-INF/persistence.xml");
		return war;
	}

	@Test
	@InSequence(1)
	public void createRoomTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("A Name");
		conference.setStart(sdf.parse("01.01.2015 12:00:00"));
		conference.setEnd(sdf.parse("10.01.2015 12:00:00"));

		conferenceService.createConference(conference);

		Assert.assertTrue(conference.getId() > 0);

		Room room = new Room();
		room.setCapacity(1000);
		room.setName("Audimax");
		room.setConference(conference);

		roomService.createRoom(room);

		Assert.assertTrue(room.getId() > 0);

	}

	@Test
	@InSequence(2)
	public void updateRoomTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("A Name");
		conference.setStart(sdf.parse("01.01.2015 12:00:00"));
		conference.setEnd(sdf.parse("10.01.2015 12:00:00"));

		conferenceService.createConference(conference);

		Assert.assertTrue(conference.getId() > 0);

		Room room = new Room();
		room.setCapacity(1000);
		room.setName("Audimax");
		room.setConference(conference);

		roomService.createRoom(room);

		Assert.assertTrue(room.getId() > 0);

		room.setName("OpelVector");

		Room updatedRoom = roomService.updateRoom(room);

		Assert.assertTrue(updatedRoom.getId() > 0);

		Assert.assertEquals("OpelVector", updatedRoom.getName());

	}

	@Test(expected=RoomNotFoundException.class)
	@InSequence(3)
	public void deleteRoomTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("A Name");
		conference.setStart(sdf.parse("01.01.2015 12:00:00"));
		conference.setEnd(sdf.parse("10.01.2015 12:00:00"));

		conferenceService.createConference(conference);

		Assert.assertTrue(conference.getId() > 0);

		Room room = new Room();
		room.setCapacity(1000);
		room.setName("Audimax");
		room.setConference(conference);

		roomService.createRoom(room);

		Assert.assertTrue(room.getId() > 0);

		roomService.deleteRoom(room.getId());

		roomService.findRoomById(room.getId());

	}

	@Test
	@InSequence(4)
	public void findRoomeByIdTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("A Name");
		conference.setStart(sdf.parse("01.01.2015 12:00:00"));
		conference.setEnd(sdf.parse("10.01.2015 12:00:00"));

		conferenceService.createConference(conference);
		
		
		Room room = new Room();
		room.setCapacity(1000);
		room.setName("Audimax");
		room.setConference(conference);

		roomService.createRoom(room);

		Assert.assertTrue(room.getId() > 0);

		Room foundRoom = roomService.findRoomById(room.getId());

		Assert.assertEquals(room.getId(), foundRoom.getId());

	}

	@Test
	@InSequence(5)
	public void findRoomByNameTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("A Name");
		conference.setStart(sdf.parse("01.01.2015 12:00:00"));
		conference.setEnd(sdf.parse("10.01.2015 12:00:00"));

		conferenceService.createConference(conference);
		
		Room room = new Room();
		room.setCapacity(1000);
		room.setName("Audimax");
		room.setConference(conference);

		roomService.createRoom(room);

		Assert.assertTrue(room.getId() > 0);

		List<Room> foundRooms = roomService.findRoomsByName(room.getName());

		Assert.assertTrue(foundRooms.size() > 0);

		Assert.assertEquals("Audimax", foundRooms.get(0).getName());
	}

	@Test
	@InSequence(6)
	public void findAllTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("A Name");
		conference.setStart(sdf.parse("01.01.2015 12:00:00"));
		conference.setEnd(sdf.parse("10.01.2015 12:00:00"));

		conferenceService.createConference(conference);
		

		Room r1 = new Room();
		r1.setCapacity(1000);
		r1.setName("Audimax2");
		r1.setConference(conference);

		roomService.createRoom(r1);

		Room r2 = new Room();
		r2.setCapacity(1000);
		r2.setName("Audimax2");
		r2.setConference(conference);

		roomService.createRoom(r2);

		Assert.assertTrue(r1.getId() > 0);
		Assert.assertTrue(r2.getId() > 0);

		List<Room> foundRooms = roomService.findAll();

		Assert.assertTrue(foundRooms.size() > 0);

	}

	@Test
	@InSequence(7)
	public void findRoomsByConferenceIdTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("A Name");
		conference.setStart(sdf.parse("01.01.2015 12:00:00"));
		conference.setEnd(sdf.parse("10.01.2015 12:00:00"));;

		conferenceService.createConference(conference);

		Assert.assertTrue(conference.getId() > 0);

		Room room = new Room();
		room.setCapacity(1000);
		room.setName("Audimax");
		room.setConference(conference);

		roomService.createRoom(room);

		Assert.assertTrue(room.getId() > 0);

		List<Room> rooms = roomService.findRoomsByConferenceId(conference
				.getId());

		Assert.assertTrue(rooms.size() > 0);
		Assert.assertEquals(room.getName(), rooms.get(0).getName());
	}
}
