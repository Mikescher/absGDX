package de.samdev.absgdx.framework.menu.attributes;

/**
 * A Tuple for extended rects (eg Menu Element Padding)
 * 
 * Useful for example for padding / margin
 *
 */
public class ExtendedRectangleRadius {

	/** top side value*/
	public final int top;
	/** left side value*/
	public final int left;
	/** bottom side value*/
	public final int bottom;
	/** right side value*/
	public final int right;

	/** offset North value*/
	public final int offsetN;
	/** offset East value*/
	public final int offsetE;
	/** offset South value*/
	public final int offsetS;
	/** offset West value*/
	public final int offsetW;

	/** inner Width value*/
	public final int innerWidth;
	/** inner Height value*/
	public final int innerHeight;
	
	/**
	 * Create a new RectangleRadius
	 * 
	 * @param left left side value
	 * @param top top side value
	 * @param bottom bottom side value
	 * @param right right side value
	 * @param offN offset north value
	 * @param offE offset east value
	 * @param offS offset south value
	 * @param offW offset west value
	 * @param elemW elementWidth value
	 * @param elemH elementHeight value
	 */
	public ExtendedRectangleRadius(int top, int left, int bottom, int right, int offN, int offE, int offS, int offW, int elemW, int elemH) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;

		this.offsetN = Math.min(offN, elemH / 2);
		this.offsetE = Math.min(offE, elemW / 2);
		this.offsetS = Math.min(offS, elemH / 2);
		this.offsetW = Math.min(offW, elemW / 2);
		
		this.innerWidth  = elemW - (offsetW + offsetS);
		this.innerHeight = elemH - (offsetN + offsetS);
	}

	/**
	 * Create a new zeroed RectangleRadius
	 */
	public ExtendedRectangleRadius() {
		this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}
	
	/**
	 * @return the sum of left and right
	 */
	public int getHorizontalSum() {
		return left + right;
	}

	/**
	 * @return teh sum of top and bottom
	 */
	public int getVerticalSum() {
		return top + bottom;
	}
}
