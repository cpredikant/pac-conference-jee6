package de.predikant.conference.service.jmx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
		long worstValue = Long.MIN_VALUE;

		PerformanceMonitoringEntry worstEntry = null;

		for (Entry<String, PerformanceMonitoringEntry> entry : entries
				.entrySet()) {
			if (entry.getValue().getMaxTime() > worstValue) {
				worstValue = entry.getValue().getMaxTime();
				worstEntry = entry.getValue();
			}
		}
		return worstEntry;
	}

	@Override
	public PerformanceMonitoringEntry getWorstByAverage() {
		float worstValue = Float.MIN_VALUE;

		PerformanceMonitoringEntry worstEntry = null;

		for (Entry<String, PerformanceMonitoringEntry> entry : entries
				.entrySet()) {
			if (entry.getValue().getAverage() > worstValue) {
				worstValue = entry.getValue().getAverage();
				worstEntry = entry.getValue();
			}
		}
		return worstEntry;
	}

	@Override
	public PerformanceMonitoringEntry getWorstByCount() {
		long worstValue = Long.MIN_VALUE;

		PerformanceMonitoringEntry worstEntry = null;

		for (Entry<String, PerformanceMonitoringEntry> entry : entries
				.entrySet()) {
			if (entry.getValue().getCount() > worstValue) {
				worstValue = entry.getValue().getCount();
				worstEntry = entry.getValue();
			}
		}
		return worstEntry;
	}

	@Override
	public void dump() {
		// TODO Auto-generated method stub

	}

}
