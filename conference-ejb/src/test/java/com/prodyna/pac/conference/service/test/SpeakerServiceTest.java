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
import com.prodyna.pac.conference.client.api.SpeakerService;
import com.prodyna.pac.conference.client.api.SpeakerService;
import com.prodyna.pac.conference.client.model.Conference;
import com.prodyna.pac.conference.client.model.Speaker;

@RunWith(Arquillian.class)
public class SpeakerServiceTest {

	@Inject
	Logger logger;

	@Inject
	private SpeakerService speakerService;

	@Deployment
	public static Archive<?> createTestArchive() {

		WebArchive war = ShrinkWrap.create(WebArchive.class,
				"SpeakerServiceTest.war");
		war.addPackages(true, "com.prodyna.pac.conference");
		war.addAsResource("META-INF/test-beans.xml", "META-INF/beans.xml");
		war.addAsResource("META-INF/test-persistence.xml",
				"META-INF/persistence.xml");
		return war;
	}

	@Test
	@InSequence(1)
	public void createSpeakerTest() throws Exception {

	}

	@Test
	@InSequence(2)
	public void updateSpeakerTest() throws Exception {

	}

	@Test
	@InSequence(3)
	public void deleteSpeakerTest() throws Exception {

	}

	@Test
	@InSequence(4)
	public void findSpeakereByIdTest() throws Exception {
		Speaker room = new Speaker();

	}

	@Test
	@InSequence(5)
	public void findSpeakersByNameTest() throws Exception {

	}

	@Test
	@InSequence(6)
	public void findAllTest() throws Exception {

	}

}
