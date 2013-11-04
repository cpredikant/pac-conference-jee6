package com.prodyna.pac.conference.service.jmx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PerformanceMonitoring implements PerformanceMonitoringMXBean,
		Serializable {

	private static final long serialVersionUID = 1L;

	private HashMap<String, PerformanceMonitoringEntry> entries = new HashMap<String, PerformanceMonitoringEntry>();

	@Override
	public void reset() {
		entries.clear();
	}

	@Override
	public synchronized void report(String service, String method, long time) {
		String key = service + ":" + method;
		PerformanceMonitoringEntry pme = entries.get(key);

		if (pme == null) {
			pme = new PerformanceMonitoringEntry(service, method);
			entries.put(key, pme);
		}
		pme.report(time);

	}

	@Override
	public List<PerformanceMonitoringEntry> getAll() {

		return new ArrayList<PerformanceMonitoringEntry>(entries.values());
	}

	@Override
	public int getCount() {

		return entries.size();
	}

	@Override
	public PerformanceMonitoringEntry getWorstByTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerformanceMonitoringEntry getWorstByAverage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerformanceMonitoringEntry getWorstByCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dump() {
		// TODO Auto-generated method stub

	}

}
