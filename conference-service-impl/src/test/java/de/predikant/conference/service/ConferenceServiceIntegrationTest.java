package de.predikant.conference.service;

import de.predikant.conference.service.api.ConferenceService;
import de.predikant.conference.service.exception.ConferenceNotFoundException;
import de.predikant.conference.service.model.Conference;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(Arquillian.class)
public class ConferenceServiceIntegrationTest {

    @Inject
    private ConferenceService conferenceService;

    @Deployment
    public static Archive<?> createTestArchive() {

        final WebArchive war = ShrinkWrap.create(WebArchive.class,
                "ConferenceServiceTest.war");
        war.addPackages(true, "de.predikant.conference");
        war.addAsResource("META-INF/test-beans.xml", "META-INF/beans.xml");
        war.addAsResource("META-INF/test-persistence.xml",
                "META-INF/persistence.xml");

        return war;
    }

    @Test
    @InSequence(1)
    public void createConferenceTest() throws Exception {
        final Conference conference = new Conference();
        conference.setDescription("A description");
        conference.setName("A Name");
        conference.setStart(LocalDateTime.now().plusMonths(1));
        conference.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(conference);

        Assert.assertTrue(conference.getId() > 0);

    }

    @Test
    @InSequence(2)
    public void updateConferenceTest() throws Exception {
        final Conference conference = new Conference();
        conference.setDescription("A description");
        conference.setName("A Name");
        conference.setStart(LocalDateTime.now().plusMonths(1));
        conference.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(conference);

        Assert.assertTrue(conference.getId() > 0);

        conference.setName("Another name");

        final Conference updatedConference = conferenceService
                .updateConference(conference);

        Assert.assertTrue(updatedConference.getId() > 0);

        Assert.assertEquals("Another name", updatedConference.getName());

    }

    @Test(expected = ConferenceNotFoundException.class)
    @InSequence(3)
    public void deleteConferenceTest() throws Exception {
        final Conference conference = new Conference();
        conference.setDescription("A description");
        conference.setName("A Name");
        conference.setStart(LocalDateTime.now().plusMonths(1));
        conference.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(conference);

        Assert.assertTrue(conference.getId() > 0);

        conferenceService.deleteConference(conference.getId());

        conferenceService.findConferenceById(conference.getId());

    }

    @Test
    @InSequence(4)
    public void findConferenceByIdTest() throws Exception {
        final Conference conference = new Conference();
        conference.setDescription("A description");
        conference.setName("findyById");
        conference.setStart(LocalDateTime.now().plusMonths(1));
        conference.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(conference);

        Assert.assertTrue(conference.getId() > 0);

        final Conference foundConference = conferenceService
                .findConferenceById(conference.getId());

        Assert.assertEquals(conference.getId(), foundConference.getId());

    }

    @Test
    @InSequence(5)
    public void findConferenceByNameTest() throws Exception {
        final Conference conference = new Conference();
        conference.setDescription("A description");
        conference.setName("findyByname");
        conference.setStart(LocalDateTime.now().plusMonths(1));
        conference.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(conference);

        Assert.assertTrue(conference.getId() > 0);

        final List<Conference> foundConferences = conferenceService
                .findConferenceByName("findyByname");

        Assert.assertTrue(foundConferences.size() > 0);

        Assert.assertEquals("findyByname", foundConferences.get(0).getName());
    }

    @Test
    @InSequence(6)
    public void findAllTest() throws Exception {

        final Conference c1 = new Conference();
        c1.setDescription("A description");
        c1.setName("findAll1");
        c1.setStart(LocalDateTime.now().plusMonths(1));
        c1.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(c1);

        final Conference c2 = new Conference();
        c2.setDescription("A description");
        c2.setName("findAll2");
        c2.setStart(LocalDateTime.now().plusMonths(1));
        c2.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(c2);

        Assert.assertTrue(c1.getId() > 0);

        final List<Conference> foundConferences = conferenceService.findAll();

        Assert.assertTrue(foundConferences.size() > 0);

    }

}
