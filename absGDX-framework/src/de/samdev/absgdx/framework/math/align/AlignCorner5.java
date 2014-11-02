package de.samdev.absgdx.framework.math.align;

import com.badlogic.gdx.math.Vector2;

/**
 * Represents an Alignment (the 4 corners + the middle)
 */
public enum AlignCorner5 {
	/**
	 * TOP LEFT
	 */
	TOPLEFT,
	
	/**
	 * TOP RIGHT
	 */
	TOPRIGHT,
	
	/**
	 * BOTTOM RIGHT
	 */
	BOTTOMRIGHT,
	
	/**
	 * BOTTOM LEFT
	 */
	BOTTOMLEFT,
	
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
		case TOPLEFT:
			return new Vector2(-1, +1);
		case TOPRIGHT:
			return new Vector2(+1, +1);
		case BOTTOMRIGHT:
			return new Vector2(+1, -1);
		case BOTTOMLEFT:
			return new Vector2(-1, -1);
		case CENTER:
			return new Vector2(00, 00);

		default:
			return null;
		}
	}
}
