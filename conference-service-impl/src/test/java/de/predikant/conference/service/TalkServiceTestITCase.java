package de.predikant.conference.service;

import de.predikant.conference.service.api.ConferenceService;
import de.predikant.conference.service.api.RoomService;
import de.predikant.conference.service.api.TalkService;
import de.predikant.conference.service.model.Conference;
import de.predikant.conference.service.model.Room;
import de.predikant.conference.service.model.Talk;
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
public class TalkServiceTestITCase {

    @Inject
    private RoomService roomService;

    @Inject
    private ConferenceService conferenceService;

    @Inject
    private TalkService talkService;

    @Deployment
    public static Archive<?> createTestArchive() {

        final WebArchive war = ShrinkWrap.create(WebArchive.class,
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

        final Conference c = new Conference();
        c.setName("Conference");
        c.setDescription("Super Conference");
        c.setStart(LocalDateTime.now().plusMonths(1));
        c.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(c);

        final Room r = new Room();
        r.setName("Room");
        r.setCapacity(10);
        r.setConference(c);

        roomService.createRoom(r);

        final Talk talk = new Talk();
        talk.setDescription("Description");
        talk.setDuration(60);
        talk.setName("Talk");
        talk.setStart(LocalDateTime.now().plusMonths(1).plusHours(1));
        talk.setConference(c);
        talk.setRoom(r);

        talkService.createTalk(talk);

        Assert.assertTrue(talk.getId() > 0);
    }

    @Test
    @InSequence(2)
    public void updateTalkTest() throws Exception {
        final Conference c = new Conference();
        c.setName("Conference");
        c.setDescription("Super Conference");
        c.setStart(LocalDateTime.now().plusMonths(1));
        c.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(c);

        final Room r = new Room();
        r.setName("Room");
        r.setCapacity(10);
        r.setConference(c);

        roomService.createRoom(r);

        final Talk talk = new Talk();
        talk.setDescription("Description");
        talk.setDuration(60);
        talk.setName("Talk");
        talk.setStart(LocalDateTime.now().plusMonths(1).plusHours(1));
        talk.setConference(c);
        talk.setRoom(r);

        talkService.createTalk(talk);

        Assert.assertTrue(talk.getId() > 0);

        talk.setName("Updated Talk");

        final Talk updatedTalk = talkService.updateTalk(talk);

        Assert.assertEquals("Updated Talk", updatedTalk.getName());
    }

    @Test()
    @InSequence(3)
    public void deleteTalkTest() throws Exception {
        final Conference c = new Conference();
        c.setName("Conference");
        c.setDescription("Super Conference");
        c.setStart(LocalDateTime.now().plusMonths(1));
        c.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(c);

        final Room r = new Room();
        r.setName("Room");
        r.setCapacity(10);
        r.setConference(c);

        roomService.createRoom(r);

        final Talk talk = new Talk();
        talk.setDescription("Description");
        talk.setDuration(60);
        talk.setName("Talk");
        talk.setStart(LocalDateTime.now().plusMonths(1).plusHours(1));
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
        final Conference c = new Conference();
        c.setName("Conference");
        c.setDescription("Super Conference");
        c.setStart(LocalDateTime.now().plusMonths(1));
        c.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(c);

        final Room r = new Room();
        r.setName("Room");
        r.setCapacity(10);
        r.setConference(c);

        roomService.createRoom(r);

        final Talk talk = new Talk();
        talk.setDescription("Description");
        talk.setDuration(60);
        talk.setName("Talk");
        talk.setStart(LocalDateTime.now().plusMonths(1).plusHours(1));
        talk.setConference(c);
        talk.setRoom(r);

        talkService.createTalk(talk);

        Assert.assertTrue(talk.getId() > 0);

        final Talk foundTalk = talkService.findTalkById(talk.getId());

        Assert.assertEquals(talk.getId(), foundTalk.getId());
    }

    @Test
    @InSequence(5)
    public void findTalksByNameTest() throws Exception {

        final Conference c = new Conference();
        c.setName("Conference");
        c.setDescription("Super Conference");
        c.setStart(LocalDateTime.now().plusMonths(1));
        c.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(c);

        final Room r = new Room();
        r.setName("Room");
        r.setCapacity(10);
        r.setConference(c);

        roomService.createRoom(r);


        final Talk t1 = new Talk();
        t1.setDescription("Description");
        t1.setDuration(60);
        t1.setName("Talk");
        t1.setStart(LocalDateTime.now().plusMonths(1).plusHours(1));
        t1.setConference(c);
        t1.setRoom(r);

        talkService.createTalk(t1);

        final Talk t2 = new Talk();
        t2.setDescription("Description");
        t2.setDuration(60);
        t2.setName("Talk");
        t2.setStart(LocalDateTime.now().plusMonths(1).plusHours(2));
        t2.setConference(c);
        t2.setRoom(r);

        talkService.createTalk(t2);

        final List<Talk> foundTalks = talkService.findTalksByName("Talk");

        Assert.assertTrue(foundTalks.size() > 0);

        Assert.assertEquals("Talk", foundTalks.get(0).getName());
    }

    @Test
    @InSequence(6)
    public void findTalksByConferenceIdTest() throws Exception {
        final Conference conference = new Conference();
        conference.setDescription("Desription");
        conference.setName("Conference");
        conference.setStart(LocalDateTime.now().plusMonths(1));
        conference.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(conference);

        final Room r = new Room();
        r.setName("Room");
        r.setCapacity(10);
        r.setConference(conference);

        roomService.createRoom(r);

        final Talk talk = new Talk();
        talk.setDescription("Description");
        talk.setDuration(60);
        talk.setName("Talk");
        talk.setConference(conference);
        talk.setRoom(r);
        talk.setStart(LocalDateTime.now().plusMonths(1).plusHours(1));

        talkService.createTalk(talk);

        final List<Talk> foundTalks = talkService.findTalksByConferenceId(conference.getId());

        Assert.assertTrue(foundTalks.size() > 0);

        Assert.assertEquals(talk.getId(), foundTalks.get(0).getId());
    }

    @Test
    @InSequence(7)
    public void findTalksByRoomIdTest() throws Exception {
        final Conference conference = new Conference();
        conference.setDescription("Desription");
        conference.setName("Conference");
        conference.setStart(LocalDateTime.now().plusMonths(1));
        conference.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(conference);


        final Room room = new Room();
        room.setCapacity(10);
        room.setName("Room");
        room.setConference(conference);


        roomService.createRoom(room);


        final Talk talk = new Talk();
        talk.setDescription("Description");
        talk.setDuration(60);
        talk.setName("Talk");
        talk.setRoom(room);
        talk.setConference(conference);
        talk.setStart(LocalDateTime.now().plusMonths(1).plusHours(1));

        talkService.createTalk(talk);

        final List<Talk> foundTalks = talkService.findTalksByRoomId(room.getId());

        Assert.assertTrue(foundTalks.size() > 0);

        Assert.assertEquals(talk.getId(), foundTalks.get(0).getId());
    }

    @Test
    @InSequence(8)
    public void findAllTest() throws Exception {
        final Conference c = new Conference();
        c.setName("Conference");
        c.setDescription("Super Conference");
        c.setStart(LocalDateTime.now().plusMonths(1));
        c.setEnd(LocalDateTime.now().plusMonths(2));

        conferenceService.createConference(c);

        final Room r = new Room();
        r.setName("Room");
        r.setCapacity(10);
        r.setConference(c);

        roomService.createRoom(r);


        final Talk t1 = new Talk();
        t1.setDescription("Description");
        t1.setDuration(60);
        t1.setName("Talk");
        t1.setStart(LocalDateTime.now().plusMonths(1).plusHours(1));
        t1.setConference(c);
        t1.setRoom(r);
        talkService.createTalk(t1);

        final Talk t2 = new Talk();
        t2.setDescription("Description");
        t2.setDuration(60);
        t2.setName("Talk");
        t2.setConference(c);
        t2.setRoom(r);
        t2.setStart(LocalDateTime.now().plusMonths(1).plusHours(2));

        talkService.createTalk(t2);

        final List<Talk> foundTalks = talkService.findAll();

        Assert.assertTrue(foundTalks.size() > 0);

    }

}
