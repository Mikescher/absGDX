package de.samdev.absgdx.framework.math.align;

import com.badlogic.gdx.math.Vector2;

/**
 * Represents an Alignment (the 4 edges + the middle)
 */
public enum AlignEdge5 {
	/**
	 * TOP
	 */
	TOP,
	
	/**
	 * LEFT
	 */
	LEFT,
	
	/**
	 * RIGHT
	 */
	RIGHT,
	
	/**
	 * BOTTOM
	 */
	BOTTOM,
	
	/**
	 * CENTER
	 */
	CENTER;
	
	/**
	 * Gets the direction as an normalized Vector
	 * 
	 * @return
	 */
	public Vector2 getDirectionVector()
	{
		switch (this) {
		case TOP:
			return new Vector2(00, +1);
		case RIGHT:
			return new Vector2(+1, 00);
		case BOTTOM:
			return new Vector2(00, -1);
		case LEFT:
			return new Vector2(-1, 00);
		case CENTER:
			return new Vector2(00, 00);

		default:
			return null;
		}
	}
}
