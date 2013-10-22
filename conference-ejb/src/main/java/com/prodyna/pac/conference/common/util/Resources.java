package com.prodyna.pac.conference.common.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;

public class Resources {

	@Produces
	@PersistenceContext
	private EntityManager em;

	@Produces
	public org.slf4j.Logger produceSlf4JLog(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMember()
				.getDeclaringClass().getName());
	}

}
