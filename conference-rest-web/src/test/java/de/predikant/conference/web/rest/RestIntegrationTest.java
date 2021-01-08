package de.predikant.conference.web.rest;

import de.predikant.conference.service.model.Conference;
import de.predikant.conference.web.rest.api.secure.ConferenceSecureRestService;
import de.predikant.conference.web.rest.api.unsecure.ConferenceUnsecureRestService;
import org.apache.http.HttpStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(Arquillian.class)
public class RestIntegrationTest {


    @Deployment(testable = false)
    public static Archive<?> createTestArchive() {

        final WebArchive archive = ShrinkWrap
                .create(WebArchive.class, "rest-test.war")
                .addPackages(true, "de.predikant.conference.web.rest")
                .addPackages(true, "de.predikant.conference.service")
                .addAsWebInfResource("META-INF/test-beans.xml", "beans.xml")
                .addAsResource("META-INF/test-persistence.xml",
                        "META-INF/persistence.xml");

        return archive;
    }

    @ArquillianResource
    private URL contextPath;

    @BeforeClass
    public static void beforeClass() {
        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
    }

    @Test
    @RunAsClient
    public void testPublicConferenceRestService() throws Exception {

        final ResteasyClient client = new ResteasyClientBuilder().build();
        final ResteasyWebTarget target = client.target(contextPath.toString()
                + "rest");

        final ConferenceUnsecureRestService conferenceUnsecureRestService = target
                .proxy(ConferenceUnsecureRestService.class);

        final Response response = conferenceUnsecureRestService
                .listAllConferences();

        Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
        response.close();

    }


    @Test
    @RunAsClient
    @Ignore
    public void testConferenceRestService() throws Exception {
        //TODO: LocalDateTimeMapping

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(contextPath.toString()
                + "rest");

        final ConferenceSecureRestService conferenceSecureRestService = target
                .proxy(ConferenceSecureRestService.class);

        final Conference conference = new Conference();
        conference.setDescription("Description of Conference");
        conference.setName("Conference");
        conference.setStart(LocalDateTime.now().plusMonths(1));
        conference.setEnd(LocalDateTime.now().plusMonths(2));

        Response response = conferenceSecureRestService
                .createConference(conference);
        Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
        response.close();

        client = new ResteasyClientBuilder().build();
        target = client.target(contextPath.toString() + "rest");

        final ConferenceUnsecureRestService conferenceUnsecureRestService = target
                .proxy(ConferenceUnsecureRestService.class);

        response = conferenceUnsecureRestService
                .listConferencesByName("Conference");

        final List<Conference> conferences = response.readEntity(new GenericType<>() {
        });

        Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
        Assert.assertTrue(conferences.size() > 0);
        response.close();

        for (final Conference c : conferences) {
            response = conferenceSecureRestService.deleteConference(String
                    .valueOf(c.getId()));
            Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
            response.close();
        }

    }

}
