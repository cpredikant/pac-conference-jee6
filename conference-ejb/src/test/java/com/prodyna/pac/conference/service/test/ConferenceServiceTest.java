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
import com.prodyna.pac.conference.client.model.Conference;

@RunWith(Arquillian.class)
public class ConferenceServiceTest {

	@Inject
	Logger logger;

	@Inject
	private ConferenceService conferenceService;
	
	@Inject
	private Date dateInFuture;

	@Deployment
	public static Archive<?> createTestArchive() {

		WebArchive war = ShrinkWrap.create(WebArchive.class,
				"ConferenceServiceTest.war");
		war.addPackages(true, "com.prodyna.pac.conference");
		war.addAsResource("META-INF/test-beans.xml", "META-INF/beans.xml");
		war.addAsResource("META-INF/test-persistence.xml",
				"META-INF/persistence.xml");
		return war;
	}

	@Test
	@InSequence(1)
	public void createConferenceTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("A Name");
		conference.setStart(dateInFuture);
		conference.setEnd(dateInFuture);

		conferenceService.createConference(conference);

		Assert.assertTrue(conference.getId() > 0);

	}

	@Test
	@InSequence(2)
	public void updateConferenceTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("A Name");
		conference.setStart(dateInFuture);
		conference.setEnd(dateInFuture);

		conferenceService.createConference(conference);

		Assert.assertTrue(conference.getId() > 0);

		conference.setName("Another name");

		Conference updatedConference = conferenceService
				.updateConference(conference);

		Assert.assertTrue(updatedConference.getId() > 0);

		Assert.assertEquals("Another name", updatedConference.getName());

	}

	@Test
	@InSequence(3)
	public void deleteConferenceTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("A Name");
		conference.setStart(dateInFuture);
		conference.setEnd(dateInFuture);

		conferenceService.createConference(conference);

		Assert.assertTrue(conference.getId() > 0);

		conferenceService.deleteConference(conference);

		Conference deletedConference = conferenceService
				.findConferenceById(conference.getId());

		Assert.assertNull(deletedConference);

	}

	@Test
	@InSequence(4)
	public void findConferenceByIdTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("findyById");
		conference.setStart(dateInFuture);
		conference.setEnd(dateInFuture);

		conferenceService.createConference(conference);

		Assert.assertTrue(conference.getId() > 0);

		Conference foundConference = conferenceService
				.findConferenceById(conference.getId());

		Assert.assertEquals(conference.getId(), foundConference.getId());

	}

	@Test
	@InSequence(5)
	public void findConferenceByNameTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("findyByname");
		conference.setStart(dateInFuture);
		conference.setEnd(dateInFuture);

		conferenceService.createConference(conference);

		Assert.assertTrue(conference.getId() > 0);

		List<Conference> foundConferences = conferenceService
				.findConferenceByName("findyByname");

		Assert.assertTrue(foundConferences.size() > 0);

		Assert.assertEquals("findyByname", foundConferences.get(0).getName());
	}
	
	
	@Test
	@InSequence(6)
	public void findAllTest() throws Exception {
		
		Conference c1 = new Conference();
		c1.setDescription("A description");
		c1.setName("findAll1");
		c1.setStart(dateInFuture);
		c1.setEnd(dateInFuture);

		conferenceService.createConference(c1);
		
		
		Conference c2 = new Conference();
		c2.setDescription("A description");
		c2.setName("findAll2");
		c2.setStart(dateInFuture);
		c2.setEnd(dateInFuture);

		conferenceService.createConference(c2);

		Assert.assertTrue(c1.getId() > 0);

		List<Conference> foundConferences = conferenceService.findAll();

		Assert.assertTrue(foundConferences.size() > 0);

	}

}