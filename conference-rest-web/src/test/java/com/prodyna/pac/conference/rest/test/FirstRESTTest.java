package com.prodyna.pac.conference.rest.test;

import java.io.File;
import java.net.URL;

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

import com.prodyna.pac.conference.web.rest.ConferenceRESTService;

@RunWith(Arquillian.class)
public class FirstRESTTest {

	 @Deployment(testable = false)
     public static Archive<?> createTestArchive() {
             File[] libs = Maven
                             .resolver()
                             .loadPomFromFile("pom.xml")
                             .resolve("com.prodyna.pac:conference-service-impl",
                            		 "com.prodyna.pac:conference-service-api")
                             .withoutTransitivity().asFile();

             WebArchive archive = ShrinkWrap
                             .create(WebArchive.class, "rest-test.war")
                             .addPackages(true, "com.prodyna.pac.conference.web.rest")
                             .deletePackages(true, "com.prodyna.pac.conference.web.rest.test")
                             .addAsResource("META-INF/test-beans.xml", "META-INF/beans.xml")
                             .addAsLibraries(libs);
             System.out.println(archive.toString(true));
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
	public void testConferenceRestService() throws Exception {

		ConferenceRESTService conferenceRESTService = ProxyFactory.create(
				ConferenceRESTService.class, contextPath.toString()
						+ "/conferences");

		

		ClientResponse<?> response = (ClientResponse<?>) conferenceRESTService.listAll();
		
		Assert.assertTrue(response.getStatus() == HttpStatus.SC_OK);
		
		response.releaseConnection();
	}
}
