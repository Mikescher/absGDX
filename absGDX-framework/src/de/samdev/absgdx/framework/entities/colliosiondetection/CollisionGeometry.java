package de.samdev.absgdx.framework.entities.colliosiondetection;

import com.badlogic.gdx.math.Vector2;

/**
 * A Collision Shape used in the CollisionMap.
 * 
 * Common subclasses are CollisionBox and CollisionCircle
 *
 */
public abstract class CollisionGeometry {

	protected Vector2 center = new Vector2();
	
	/**
	 * Create a new CollisionGeometry
	 */
	public CollisionGeometry() {
		// NOP
	}

	/**
	 * Gets the center X coordinate
	 * 
	 * @return
	 */
	public float getCenterX() {
		return center.x;
	}

	/**
	 * Gets the center Y coordinate
	 * 
	 * @return
	 */
	public float getCenterY() {
		return center.y;
	}

	/**
	 * Changes the center coordinate
	 * 
	 * @param x the x position
	 * @param y the y position
	 */
	public void setCenter(float x, float y) {
		center.set(x, y);
	}
	
	/**
	 * The surrounding radius of this geometry
	 * 
	 * @return
	 */
	public abstract float getRadius();
}
