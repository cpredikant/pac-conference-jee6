package com.prodyna.pac.conference.service.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.jboss.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {

	@Inject
	Logger logger;

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {

		long start = System.currentTimeMillis();

		Object proceed = ic.proceed();

		long end = System.currentTimeMillis();

		long duration = end - start;

		logger.infov("SERVICE CALL on CLASS: "
				+ ic.getTarget().getClass().getSimpleName() + " METHOD: "
				+ ic.getMethod().getName() + " PARAMETER: " + paramtersToString(ic.getParameters())
				+ " DURATION: " + duration + "ms");

		return proceed;
	}

	private String paramtersToString(Object[]  params) {
		String parameters = "";

		for (Object o : params) {
			parameters += "[" + o.getClass().getSimpleName() + " : "
					+ o.toString() + "], ";
		}
		return parameters;
	}

}
