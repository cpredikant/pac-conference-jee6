package com.prodyna.pac.conference.service.jmx;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;;

@Singleton
@Startup
public class JmxEnablerSingleton {

	@Inject
	private MBeanServer ms;

	@Inject
	private Logger logger;

	@PostConstruct
	private void postConstruct() {
		try {
			if (!ms.isRegistered(new ObjectName(
					PerformanceMonitoringMXBean.OBJECT_NAME))) {
				
				ms.registerMBean(new PerformanceMonitoring(), new ObjectName(
						PerformanceMonitoringMXBean.OBJECT_NAME));
				
			}

		} catch (Exception e) {
			logger.error("Error registering JMX-Beans", e);
		}
	}

	@PreDestroy
	private void preDestroy() {
		try {
			if (ms.isRegistered(new ObjectName(
					PerformanceMonitoringMXBean.OBJECT_NAME))) {
				
				ms.unregisterMBean(new ObjectName(
						PerformanceMonitoringMXBean.OBJECT_NAME));
				
			}
		} catch (Exception e) {
			logger.error("Error unregistering JMX-Beans", e);
		}
	}

}
