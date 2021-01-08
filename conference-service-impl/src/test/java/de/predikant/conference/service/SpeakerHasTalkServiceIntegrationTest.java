package de.predikant.conference.service;

import de.predikant.conference.service.api.*;
import de.predikant.conference.service.exception.SpeakerHasTalkNotFoundException;
import de.predikant.conference.service.model.*;
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

@RunWith(Arquillian.class)
public class SpeakerHasTalkServiceIntegrationTest {

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


    @Deployment
    public static Archive<?> createTestArchive() {

        final WebArchive war = ShrinkWrap.create(WebArchive.class,
                "SpeakerHAsTalkServiceTest.war");
        war.addPackages(true, "de.predikant.conference");
        war.addAsResource("META-INF/test-beans.xml", "META-INF/beans.xml");
        war.addAsResource("META-INF/test-persistence.xml",
                "META-INF/persistence.xml");
        return war;
    }

    @Test
    @InSequence(1)
    public void assingTest() throws Exception {
        final Conference c = new Conference();
        c.setDescription("A description");
        c.setName("findyByname");
        c.setStart(LocalDateTime.now().plusMonths(1));
        c.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(c);


        final Speaker s = new Speaker();
        s.setName("Speaker");
        s.setDescription("Speaker Description");

        speakerService.createSpeaker(s);

        final Room r = new Room();
        r.setCapacity(1000);
        r.setName("Audimax2");
        r.setConference(c);

        roomService.createRoom(r);

        final Talk t = new Talk();
        t.setName("Talk");
        t.setDescription("Talk Description");
        t.setDuration(10);
        t.setStart(LocalDateTime.now().plusMonths(1).plusHours(1));
        t.setConference(c);
        t.setRoom(r);

        talkService.createTalk(t);

        speakerHasTalkService.assign(s, t);

        final SpeakerHasTalk hasTalk = speakerHasTalkService.findSpeakerHasTalkBySpeakerAndTalk(s, t);

        Assert.assertNotNull(hasTalk);


    }

    @Test(expected = SpeakerHasTalkNotFoundException.class)
    @InSequence(2)
    public void unassignTest() throws Exception {

        final Conference c = new Conference();
        c.setDescription("A description");
        c.setName("findyByname");
        c.setStart(LocalDateTime.now().plusMonths(1));
        c.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(c);


        final Speaker s = new Speaker();
        s.setName("Speaker");
        s.setDescription("Speaker Description");

        speakerService.createSpeaker(s);

        final Room r = new Room();
        r.setCapacity(1000);
        r.setName("Audimax2");
        r.setConference(c);

        roomService.createRoom(r);

        final Talk t = new Talk();
        t.setName("Talk");
        t.setDescription("Talk Description");
        t.setDuration(10);
        t.setStart(LocalDateTime.now().plusMonths(1).plusHours(1));
        t.setConference(c);
        t.setRoom(r);

        talkService.createTalk(t);

        speakerHasTalkService.assign(s, t);

        final SpeakerHasTalk hasTalk = speakerHasTalkService.findSpeakerHasTalkBySpeakerAndTalk(s, t);

        Assert.assertNotNull(hasTalk);

        speakerHasTalkService.unassign(s, t);

        speakerHasTalkService.findSpeakerHasTalkBySpeakerAndTalk(s, t);

    }


}
