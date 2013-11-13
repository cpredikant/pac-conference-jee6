package com.prodyna.pac.conference.web.rest.test;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;

import org.apache.http.HttpStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.pac.conference.model.Conference;
import com.prodyna.pac.conference.web.rest.api.secure.ConferenceSecureRestService;

@RunWith(Arquillian.class)
public class RestTest {

	private static SimpleDateFormat SDF;
	
	@Deployment(testable = false)
	public static Archive<?> createTestArchive() {
		File[] libs = Maven
				.resolver()
				.loadPomFromFile("pom.xml")
				.resolve("com.prodyna.pac:conference-common",
						"com.prodyna.pac:conference-service-api",
						"com.prodyna.pac:conference-service-impl")
				.withoutTransitivity().asFile();

		WebArchive archive = ShrinkWrap
				.create(WebArchive.class, "rest-test.war")
				.addPackages(true, "com.prodyna.pac.conference.web.rest")
				.deletePackages(true, "com.prodyna.pac.conference.web.rest.test")
				.addAsWebInfResource("META-INF/test-beans.xml", "beans.xml")
				.addAsLibraries(libs);
		System.out.println(archive.toString(true));
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

		// Create conference
		Conference conference = new Conference();
		conference.setDescription("Description W-JAX");
		conference.setName("W-JAX");
		conference.setStart(SDF.parse("12.12.2013 08:00:00"));
		conference.setEnd(SDF.parse("16.12.2013 17:00:00"));

		ClientResponse<?> response = (ClientResponse<?>) conferenceSecureRestService
				.createConference(conference);
		Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
		response.releaseConnection();

		
	}

	

}
