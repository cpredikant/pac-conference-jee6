package de.predikant.conference.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.management.MBeanServer;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.management.ManagementFactory;


public class Resources {

    @Produces
    @Dependent
    @PersistenceContext(unitName = "conference-pu")
    private EntityManager entityManager;

    @Produces
    public Logger produceSlf4JLog(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    public MBeanServer produceMBeanServer() {
        return ManagementFactory.getPlatformMBeanServer();
    }

    @Produces
    public InitialContext produceIC() {
        try {
            return new InitialContext();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }


}
