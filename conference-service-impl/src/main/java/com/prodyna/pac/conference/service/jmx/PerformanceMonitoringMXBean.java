package com.prodyna.pac.conference.service.jmx;

import java.util.List;

public interface PerformanceMonitoringMXBean {
    
    static final String OBJECT_NAME = "com.prodyna.pac.conference.service.jmx.performanceMonitoring:service=PerformanceMonitoring";
    
    void reset();
    
    void report(String service, String method, long time);
    
    List<PerformanceMonitoringEntry> getAll();
    
    int getCount();
    
    PerformanceMonitoringEntry getWorstByTime();
    
    PerformanceMonitoringEntry getWorstByAverage();
    
    PerformanceMonitoringEntry getWorstByCount();
    
    void dump();

}
