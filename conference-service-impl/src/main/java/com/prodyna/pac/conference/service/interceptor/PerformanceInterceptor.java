package com.prodyna.pac.conference.service.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.management.MBeanServer;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;

import com.prodyna.pac.conference.service.jmx.PerformanceMonitoringMXBean;

@Performance
@Interceptor
public class PerformanceInterceptor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Inject
    private MBeanServer ms;



	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		
		long start = System.currentTimeMillis();

		Object proceed = ic.proceed();

		long end = System.currentTimeMillis();

		long duration = end - start;
		
		PerformanceMonitoringMXBean performance = MBeanServerInvocationHandler
				.newProxyInstance(ms, new ObjectName(
						PerformanceMonitoringMXBean.OBJECT_NAME),
						PerformanceMonitoringMXBean.class, false);

		performance.report(ic.getTarget().getClass().getSimpleName(), ic
				.getMethod().getName(), duration);
		
		return proceed;
	}

}
