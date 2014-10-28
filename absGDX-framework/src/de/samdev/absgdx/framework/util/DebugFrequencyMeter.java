package de.samdev.absgdx.framework.util;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

public class DebugFrequencyMeter {
	private final static long REFRESH_INTERVAL = 1000;

	public final float targetFPS;

	// #########################################################################

	private long intervalStartTime = 0;
	private int intervalCounter = 0;

	private int renderIntervalCounter = 0;
	private int updateIntervalCounter = 0;

	// #########################################################################

	private long startRenderTime;
	private long startUpdateTime;

	private long totalRenderTime;
	private long totalUpdateTime;

	// #########################################################################

	public float fps = 0;

	// #########################################################################

	public double renderTime;
	public double updateTime;
	public double totalTime;

	// #########################################################################

	private long lastGCUpdateTime = 0;

	private long gcInitCount = -1;
	private long gcInitTime = -1;

	public long gcCount;
	public long gcTime;
	public long gcTimeBetweenGC;
	public float gcTimePerGC;

	// #########################################################################

	public DebugFrequencyMeter(int targetFPS) {
		super();

		this.targetFPS = targetFPS;
	}

	public DebugFrequencyMeter() {
		this(60);
	}

	public void startCycle() {
		final long delta = System.currentTimeMillis() - intervalStartTime;

		if (delta > REFRESH_INTERVAL) {
			fps = (intervalCounter * REFRESH_INTERVAL * 1f) / delta;

			intervalStartTime += delta;
			intervalCounter = 0;

			// #########################

			renderTime = totalRenderTime * 1d / renderIntervalCounter;
			updateTime = totalUpdateTime * 1d / updateIntervalCounter;

			totalTime = renderTime + updateTime;

			totalRenderTime = 0;
			renderIntervalCounter = 0;
			totalUpdateTime = 0;
			updateIntervalCounter = 0;
		}

		intervalCounter++;

		// #####################################################################

		long totalGarbageCollections = 0;
		long garbageCollectionTime = 0;
		for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
			totalGarbageCollections += gc.getCollectionCount();
			garbageCollectionTime += gc.getCollectionTime();
		}
		
		if (totalGarbageCollections > gcCount) {
			gcTimeBetweenGC = System.currentTimeMillis() - lastGCUpdateTime;
			lastGCUpdateTime = System.currentTimeMillis();
		}
		gcTimePerGC = (gcTime - gcInitTime) / (gcCount - gcInitCount * 1f);
		
		gcCount = totalGarbageCollections;
		gcTime = garbageCollectionTime;
	}

	public void startRender() {
		startRenderTime = System.nanoTime();
	}

	public void endRender() {
		totalRenderTime += System.nanoTime() - startRenderTime;

		renderIntervalCounter++;
	}

	public void startUpdate() {
		startUpdateTime = System.nanoTime();
	}

	public void endUpdate() {
		totalUpdateTime += System.nanoTime() - startUpdateTime;

		updateIntervalCounter++;
	}

	public double getRenderPercentage() {
		return renderTime / (10000000d / targetFPS);
	}

	public double getUpdatePercentage() {
		return updateTime / (10000000d / targetFPS);
	}

	public double getTotalPercentage() {
		return (renderTime + updateTime) / (10000000d / targetFPS);
	}
}
