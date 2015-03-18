package de.samdev.absgdx.framework.util;

import com.badlogic.gdx.Gdx;

/**
 * An logger for the current fps / ups etc
 */
public class DebugFrequencyMeter {
	private final static long REFRESH_INTERVAL = 1000;

	/**
	 * The targeted FPS rate
	 */
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

	/** The current FPS */
	public float fps = 0;

	// #########################################################################

	/** The current time per render process */
	public double renderTime;
	/** The current time per update process */
	public double updateTime;
	/** The current time per (render + update) process */
	public double totalTime;

	// #########################################################################

	private long lastGCUpdateTime = System.currentTimeMillis();
	private long gcAllocMem = 0;

	/** The amount of GC calls */
	public long gcCount = 0;
	/** The time between GC calls */
	public long gcTimeBetweenGC;

	// #########################################################################

	/**
	 * Creates a new DebugFrequencyMeter
	 * 
	 * @param targetFPS the TargetFPS of LibGDX
	 */
	public DebugFrequencyMeter(int targetFPS) {
		super();

		this.targetFPS = targetFPS;
	}

	/**
	 * Create a new DebugFrequencyMeter with TargetFPS (=60)
	 */
	public DebugFrequencyMeter() {
		this(60);
	}

	/**
	 * Call this at the start of a new cycle
	 */
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

		long allocmemory = Gdx.app.getJavaHeap();
		
		if (allocmemory < (this.gcAllocMem - 2048)) {
			gcTimeBetweenGC = System.currentTimeMillis() - lastGCUpdateTime;
			lastGCUpdateTime = System.currentTimeMillis();
			
			gcCount++;
		}
		
		this.gcAllocMem = allocmemory;
	}

	/**
	 * Call this at the start of the rendering
	 */
	public void startRender() {
		startRenderTime = System.nanoTime();
	}

	/**
	 * Call this at the end of the rendering
	 */
	public void endRender() {
		totalRenderTime += System.nanoTime() - startRenderTime;

		renderIntervalCounter++;
	}

	/**
	 * Call this at the start of the updating
	 */
	public void startUpdate() {
		startUpdateTime = System.nanoTime();
	}

	/**
	 * Call this at the end of the updating
	 */
	public void endUpdate() {
		totalUpdateTime += System.nanoTime() - startUpdateTime;

		updateIntervalCounter++;
	}

	/**
	 * Get the percentage that the rendering takes (compared to the maximum amount of time, a cycle can take, to still reach the targetFPS)
	 * 
	 * @return the render percentage
	 */
	public double getRenderPercentage() {
		return renderTime / (10000000d / targetFPS);
	}

	/**
	 * Get the percentage that the updating takes (compared to the maximum amount of time, a cycle can take, to still reach the targetFPS)
	 * 
	 * @return the update percentage
	 */
	public double getUpdatePercentage() {
		return updateTime / (10000000d / targetFPS);
	}

	/**
	 * Get the percentage that the (rendering+updating) takes (compared to the maximum amount of time, a cycle can take, to still reach the targetFPS)
	 * 
	 * @return the total percentage
	 */
	public double getTotalPercentage() {
		return (renderTime + updateTime) / (10000000d / targetFPS);
	}
}
