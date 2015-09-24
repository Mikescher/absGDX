package de.samdev.absgdx.framework.menu.attributes;

/**
 * A 4Tuple <int, int, int, int>
 * 
 * Useful for example for padding / margin
 *
 */
public class RectangleRadius {

	/** top side value*/
	public final int top;
	/** left side value*/
	public final int left;
	/** bottom side value*/
	public final int bottom;
	/** right side value*/
	public final int right;
	
	/**
	 * Create a new RectangleRadius
	 * 
	 * @param left left side value
	 * @param top top side value
	 * @param bottom bottom side value
	 * @param right right side value
	 */
	public RectangleRadius(int top, int left, int bottom, int right) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}

	/**
	 * Create a new zeroed RectangleRadius
	 */
	public RectangleRadius() {
		this(0, 0, 0, 0);
	}
	
	/**
	 * @return the sum of left and right
	 */
	public int getHorizontalSum() {
		return left + right;
	}

	/**
	 * @return the sum of top and bottom
	 */
	public int getVerticalSum() {
		return top + bottom;
	}
}
