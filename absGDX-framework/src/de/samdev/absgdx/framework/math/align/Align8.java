package de.samdev.absgdx.framework.math.align;

import com.badlogic.gdx.math.Vector2;

/**
 * Represents an Alignment (the 4 edges + the 4 corners)
 */
public enum Align8 {
	/**
	 * TOP LEFT
	 */
	
	TOPLEFT,
	/**
	 * TOP MIDDLE
	 */
	TOP,
	
	/**
	 * TOP RIGHT
	 */
	TOPRIGHT,
	
	/**
	 * MIDDLE RIGHT
	 */
	RIGHT,
	
	/**
	 * BOTTOM RIGHT
	 */
	BOTTOMRIGHT,
	
	/**
	 * MIDDLE BOTTOM
	 */
	BOTTOM,
	
	/**
	 * BOTTOM LEFT
	 */
	BOTTOMLEFT,
	
	/**
	 * MIDDLE LEFT
	 */
	LEFT;
	
	/**
	 * Gets the direction as an normalized Vector
	 * 
	 * @return
	 */
	public Vector2 getDirectionVector()
	{
		switch (this) {
		case TOPLEFT:
			return new Vector2(-1, +1);
		case TOP:
			return new Vector2(00, +1);
		case TOPRIGHT:
			return new Vector2(+1, +1);
		case RIGHT:
			return new Vector2(+1, 00);
		case BOTTOMRIGHT:
			return new Vector2(+1, -1);
		case BOTTOM:
			return new Vector2(00, -1);
		case BOTTOMLEFT:
			return new Vector2(-1, -1);
		case LEFT:
			return new Vector2(-1, 00);

		default:
			return null;
		}
	}
}
