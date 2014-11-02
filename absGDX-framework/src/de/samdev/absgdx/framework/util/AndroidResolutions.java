package de.samdev.absgdx.framework.util;

import com.badlogic.gdx.math.GridPoint2;

/**
 * An util for different commonly used android aspect ratios
 *
 */
@SuppressWarnings("javadoc")
public final class AndroidResolutions {

	public final static GridPoint2 RES_4_3 = new GridPoint2(320, 240); // 4:3
	public final static GridPoint2 RES_9_5 = new GridPoint2(432, 240); // 9:5
	public final static GridPoint2 RES_3_2 = new GridPoint2(480, 320); // 3:2
	public final static GridPoint2 RES_5_3 = new GridPoint2(800, 480); // 5:3
	public final static GridPoint2 RES__16_9 = new GridPoint2(1280, 720); // 16:9
	public final static GridPoint2 RES_25_16 = new GridPoint2(1200, 768); // 25:16
	public final static GridPoint2 RES_8_5 = new GridPoint2(1280, 800); // 8:5
	public final static GridPoint2 RES_14_9 = new GridPoint2(1400, 900); // 14:9

	public final static GridPoint2[] RES_ALL = new GridPoint2[] { RES_4_3, RES_9_5, RES_3_2, RES_5_3, RES__16_9, RES_25_16, RES_8_5, RES_14_9 };
}
