package de.predikant.conference.service.interceptor;

import de.predikant.conference.service.jmx.PerformanceMonitoringMXBean;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.management.MBeanServer;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.io.Serializable;

@Performance
@Interceptor
public class PerformanceInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger logger;

    @Inject
    private MBeanServer ms;

    private PerformanceMonitoringMXBean performance;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {

        long start = System.currentTimeMillis();

        Object proceed = ic.proceed();

        long end = System.currentTimeMillis();

        long duration = end - start;

        getPerformance().report(ic.getTarget().getClass().getSimpleName(), ic
                .getMethod().getName(), duration);

        return proceed;
    }

    public PerformanceMonitoringMXBean getPerformance() {
        if (performance == null) {
            try {
                performance = MBeanServerInvocationHandler
                        .newProxyInstance(ms, new ObjectName(
                                        PerformanceMonitoringMXBean.OBJECT_NAME),
                                PerformanceMonitoringMXBean.class, false);
            } catch (MalformedObjectNameException e) {
                logger.error("Error creating MxBeanProxy", e);
            }
        }
        return performance;
    }

}
