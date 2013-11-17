package com.prodyna.pac.conference.web.rest.test;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.http.HttpStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.util.GenericType;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.pac.conference.service.model.Conference;
import com.prodyna.pac.conference.web.rest.api.secure.ConferenceSecureRestService;
import com.prodyna.pac.conference.web.rest.api.unsecure.ConferenceUnsecureRestService;

@RunWith(Arquillian.class)
public class RestTest {

	private static SimpleDateFormat SDF;
	
	@Deployment(testable = false)
	public static Archive<?> createTestArchive() {
	

		WebArchive archive = ShrinkWrap
				.create(WebArchive.class, "rest-test.war")
				.addPackages(true, "com.prodyna.pac.conference.web.rest")
				.deletePackages(true, "com.prodyna.pac.conference.web.rest.test")
				.addPackages(true, "com.prodyna.pac.conference.service")
				.deletePackages(true, "com.prodyna.pac.conference.service.test")
				.addPackages(true, "com.prodyna.pac.conference.common")
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

		
		ConferenceSecureRestService conferenceSecureRestService = ProxyFactory
				.create(ConferenceSecureRestService.class,
						contextPath.toString() + "private/conference");

		
		Conference conference = new Conference();
		conference.setDescription("Description of Conference");
		conference.setName("Conference");
		conference.setStart(SDF.parse("12.12.2013 12:00:00"));
		conference.setEnd(SDF.parse("16.12.2013 15:00:00"));

		ClientResponse<?> response = (ClientResponse<?>) conferenceSecureRestService
				.createConference(conference);
		Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
		response.releaseConnection();
		
		
		ConferenceUnsecureRestService conferenceUnsecureRestService = ProxyFactory
				.create(ConferenceUnsecureRestService.class,
						contextPath.toString() + "public/conference");
		
		response = (ClientResponse<?>) conferenceUnsecureRestService
				.listConferencesByName("Conference");
		List<Conference> conferences =  response.getEntity(new GenericType<List<Conference>>() {
		});
		
		Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
		Assert.assertTrue(conferences.size() > 0 );
		response.releaseConnection();
		
		for (Conference c : conferences){
		response = (ClientResponse<?>) conferenceSecureRestService
				.deleteConference(String.valueOf(c.getId()));
		Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
		response.releaseConnection();
		}
		

		
	}

	

}
