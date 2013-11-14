package com.prodyna.pac.conference.service.test;

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

import com.prodyna.pac.conference.service.api.SpeakerService;
import com.prodyna.pac.conference.service.exception.SpeakerNotFoundException;
import com.prodyna.pac.conference.service.model.Speaker;

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
		Speaker speaker = new Speaker();
		speaker.setDescription("Super Speaker");
		speaker.setName("The Speaker");

		speakerService.createSpeaker(speaker);

		Assert.assertTrue(speaker.getId() > 0);
	}

	@Test
	@InSequence(2)
	public void updateSpeakerTest() throws Exception {
		Speaker speaker = new Speaker();
		speaker.setDescription("Super Speaker");
		speaker.setName("The Speaker");

		speakerService.createSpeaker(speaker);

		speaker.setDescription("Super Duper Speaker");

		Speaker updatedSpeaker = speakerService.updateSpeaker(speaker);

		Assert.assertEquals("Super Duper Speaker",
				updatedSpeaker.getDescription());
	}

	@Test(expected = SpeakerNotFoundException.class)
	@InSequence(3)
	public void deleteSpeakerTest() throws Exception {
		Speaker speaker = new Speaker();
		speaker.setDescription("Super Speaker");
		speaker.setName("The Speaker");

		speakerService.createSpeaker(speaker);

		Assert.assertTrue(speaker.getId() > 0);

		speakerService.deleteSpeaker(speaker.getId());
		
		speakerService.findSpeakerById(speaker.getId());

	}

	@Test
	@InSequence(4)
	public void findSpeakerByIdTest() throws Exception {
		Speaker speaker = new Speaker();
		speaker.setDescription("Super Speaker");
		speaker.setName("The Speaker");

		speakerService.createSpeaker(speaker);

		Assert.assertTrue(speaker.getId() > 0);

		Speaker foundSpeaker = speakerService.findSpeakerById(speaker.getId());

		Assert.assertEquals(speaker.getId(), foundSpeaker.getId());
	}

	@Test
	@InSequence(5)
	public void findSpeakersByNameTest() throws Exception {
		Speaker s1 = new Speaker();
		s1.setDescription("Super Speaker");
		s1.setName("The Speaker");

		speakerService.createSpeaker(s1);

		Speaker s2 = new Speaker();
		s2.setDescription("Super Speaker");
		s2.setName("The Speaker");

		speakerService.createSpeaker(s2);

		List<Speaker> foundSpeakers = speakerService
				.findSpeakersByName("The Speaker");

		Assert.assertTrue(foundSpeakers.size() > 0);

		Assert.assertEquals("The Speaker", foundSpeakers.get(0).getName());
	}

	@Test
	@InSequence(6)
	public void findAllTest() throws Exception {
		Speaker s1 = new Speaker();
		s1.setDescription("Super Speaker");
		s1.setName("The Speaker");

		speakerService.createSpeaker(s1);

		Speaker s2 = new Speaker();
		s2.setDescription("Super Speaker");
		s2.setName("The Speaker");

		speakerService.createSpeaker(s2);

		List<Speaker> foundSpeakers = speakerService.findAll();

		Assert.assertTrue(foundSpeakers.size() > 0);

	}

}
