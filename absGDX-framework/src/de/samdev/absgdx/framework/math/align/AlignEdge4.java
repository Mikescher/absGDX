package de.samdev.absgdx.framework.math.align;

import com.badlogic.gdx.math.Vector2;

/**
 * Represents an Alignment (the 4 edges)
 */
public enum AlignEdge4 {
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
	BOTTOM;
	
	/**
	 * Gets the direction as an normalized Vector
	 * 
	 * @return the direction as an vector
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

		default:
			return null;
		}
	}
}
