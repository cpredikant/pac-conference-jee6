package de.predikant.conference.arschloch;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(Arquillian.class)
public class ArquillianITCase {

    @EJB(mappedName = "java:module/GreeterEJB")
    private GreeterEJB ejb;

    @Deployment
    public static Archive<?> deploy() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "testapp.war");
        war.addClass(GreeterEJB.class);
        war.addClass(HelloWorldEndpoint.class);
        war.addClass(RestApplication.class);
        return war;
    }

    @Test
    @RunAsClient
    public void testURL(@ArquillianResource URL url) throws Exception {
        String result = TestUtil.performCall(new URL(url.toString() + "hello"));
        assertEquals(result, "Hello from WildFly bootable jar!", result);
    }

    @Test
    public void testEjb() throws Exception {
        assertNotNull(ejb);
    }

}
