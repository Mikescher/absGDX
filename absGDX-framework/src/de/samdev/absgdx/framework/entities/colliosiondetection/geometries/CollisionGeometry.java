package de.samdev.absgdx.framework.entities.colliosiondetection.geometries;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionListener;

/**
 * A Collision Shape used in the CollisionMap.
 * 
 * Common subclasses are CollisionBox and CollisionCircle
 *
 */
public abstract class CollisionGeometry {

	protected Vector2 center = new Vector2();
	
	/** the Entity associated with this geometry */
	public final CollisionGeometryOwner owner;
	
	/** the collision listener */
	public final List<CollisionListener> listener = new ArrayList<CollisionListener>();
	
	/**
	 * Create a new CollisionGeometry
	 * 
	 * @param owner 
	 */
	public CollisionGeometry(CollisionGeometryOwner owner) {
		super();
		
		this.owner = owner;
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
	
	/**
	 * Calculates and returns the area of the geometry 
	 * 
	 * @return
	 */
	public abstract float area();
}
