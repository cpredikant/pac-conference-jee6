package com.prodyna.pac.conference.service.test;

import java.util.Date;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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

	@Deployment
	public static Archive<?> createTestArchive() {

		WebArchive war = ShrinkWrap.create(WebArchive.class);
		war.addPackages(true, "com.prodyna.pac.conference");
		war.addAsResource("META-INF/test-beans.xml", "META-INF/beans.xml");
		war.addAsResource("META-INF/test-persistence.xml",
				"META-INF/persistence.xml");
		return war;
	}

	@Test
	public void createConferenceTest() throws Exception {
		Conference conference = new Conference();
		conference.setDescription("A description");
		conference.setName("A Name");
		conference.setEnd(new Date());

		conferenceService.createConference(conference);

		Assert.assertTrue(conference.getId() > 0);

	}

}
