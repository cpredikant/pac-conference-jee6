package de.predikant.conference.web.rest.test;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

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
import org.junit.Test;
import org.junit.runner.RunWith;

import de.predikant.conference.service.model.Conference;
import de.predikant.conference.web.rest.api.secure.ConferenceSecureRestService;
import de.predikant.conference.web.rest.api.unsecure.ConferenceUnsecureRestService;

@RunWith(Arquillian.class)
public class RestTest {

	private static SimpleDateFormat SDF;

	@Deployment(testable = false)
	public static Archive<?> createTestArchive() {

		WebArchive archive = ShrinkWrap
				.create(WebArchive.class, "rest-test.war")
				.addPackages(true, "de.predikant.conference.web.rest")
				.deletePackages(true,
						"de.predikant.conference.web.rest.test")
				.addPackages(true, "de.predikant.conference.service")
				.deletePackages(true, "de.predikant.conference.service.test")
				.addPackages(true, "de.predikant.conference.common")
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
		SDF = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	}

	@Test
	@RunAsClient
	public void testConferenceRestService() throws Exception {

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(contextPath.toString()
				+ "private/conference");

		ConferenceSecureRestService conferenceSecureRestService = target
				.proxy(ConferenceSecureRestService.class);

		Conference conference = new Conference();
		conference.setDescription("Description of Conference");
		conference.setName("Conference");
		conference.setStart(SDF.parse("12.12.2014 12:00:00"));
		conference.setEnd(SDF.parse("16.12.2014 15:00:00"));

		Response response = conferenceSecureRestService
				.createConference(conference);

		Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
		response.close();

		client = new ResteasyClientBuilder().build();
		target = client.target(contextPath.toString() + "public/conference");

		ConferenceUnsecureRestService conferenceUnsecureRestService = target
				.proxy(ConferenceUnsecureRestService.class);

		response = conferenceUnsecureRestService
				.listConferencesByName("Conference");

		List<Conference> conferences = response.readEntity(new GenericType<List<Conference>>() {
		});

		Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
		Assert.assertTrue(conferences.size() > 0);
		response.close();

		for (Conference c : conferences) {
			response = conferenceSecureRestService.deleteConference(String
					.valueOf(c.getId()));
			Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
			response.close();
		}

	}

}
