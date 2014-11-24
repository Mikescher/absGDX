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
	/** This delta value is used as the additional distance in getTouchDistance() methods */
	public final static float FDELTA = 0.00001f;

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
	 * Gets the a copy of the center vector
	 * 
	 * @return
	 */
	public Vector2 getCenter() {
		return new Vector2(center);
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
	
	/**
	 * Get the X-distance that this geometries and another can minimally have
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets ShapeMath.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param other the other geometry
	 * @return the minimal x distance
	 */
	public float getXTouchDistance(CollisionGeometry other) {
		if (other instanceof CollisionBox) 
			return getXTouchDistance((CollisionBox)other);
		else if (other instanceof CollisionCircle) 
			return getXTouchDistance((CollisionCircle)other);
		else if (other instanceof CollisionTriangle) 
			return getXTouchDistance((CollisionTriangle)other);
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Get the X-distance that this geometries and another can minimally have 
	 * -> the other one is a CollisionBox
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets ShapeMath.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param other the other geometry
	 * @return the minimal x distance
	 */
	public abstract float getXTouchDistance(CollisionBox other);
	
	/**
	 * Get the X-distance that this geometries and another can minimally have 
	 * -> the other one is a CollisionCircle
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets ShapeMath.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param other the other geometry
	 * @return the minimal x distance
	 */
	public abstract float getXTouchDistance(CollisionCircle other);
	
	/**
	 * Get the X-distance that this geometries and another can minimally have 
	 * -> the other one is a CollisionTriangle
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets ShapeMath.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param other the other geometry
	 * @return the minimal x distance
	 */
	public abstract float getXTouchDistance(CollisionTriangle other);
	
	/**
	 * Get the Y-distance that this geometries and another can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets ShapeMath.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param other the other geometry
	 * @return the minimal y distance
	 */
	public float getYTouchDistance(CollisionGeometry other) {
		if (other instanceof CollisionBox) 
			return getYTouchDistance((CollisionBox)other);
		else if (other instanceof CollisionCircle) 
			return getYTouchDistance((CollisionCircle)other);
		else if (other instanceof CollisionTriangle) 
			return getYTouchDistance((CollisionTriangle)other);
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Get the Y-distance that this geometries and another can minimally have 
	 * -> the other one is a CollisionBox
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets ShapeMath.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param other the other geometry
	 * @return the minimal y distance
	 */
	public abstract float getYTouchDistance(CollisionBox other);
	
	/**
	 * Get the Y-distance that this geometries and another can minimally have 
	 * -> the other one is a CollisionCircle
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets ShapeMath.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param other the other geometry
	 * @return the minimal y distance
	 */
	public abstract float getYTouchDistance(CollisionCircle other);
	
	/**
	 * Get the Y-distance that this geometries and another can minimally have 
	 * -> the other one is a CollisionTriangle
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets ShapeMath.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param other the other geometry
	 * @return the minimal y distance
	 */
	public abstract float getYTouchDistance(CollisionTriangle other);
	
	/**
	 * Returns if this geometry and another are intersecting each other
	 * 
	 * @param other the other geometry
	 * @return true if [this] and [other] intersect each other
	 */
	public boolean isIntersectingWith(CollisionGeometry other) {
		if (other instanceof CollisionBox) 
			return isIntersectingWith((CollisionBox)other);
		else if (other instanceof CollisionCircle) 
			return isIntersectingWith((CollisionCircle)other);
		else if (other instanceof CollisionTriangle) 
			return isIntersectingWith((CollisionTriangle)other);
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Returns if this geometry and another (a box) are intersecting each other
	 * 
	 * @param other the other geometry
	 * @return true if [this] and [other] intersect each other
	 */
	public abstract boolean isIntersectingWith(CollisionBox other);
	
	/**
	 * Returns if this geometry and another (a circle) are intersecting each other
	 * 
	 * @param other the other geometry
	 * @return true if [this] and [other] intersect each other
	 */
	public abstract boolean isIntersectingWith(CollisionCircle other);
	
	/**
	 * Returns if this geometry and another (a triangle) are intersecting each other
	 * 
	 * @param other the other geometry
	 * @return true if [this] and [other] intersect each other
	 */
	public abstract boolean isIntersectingWith(CollisionTriangle other);
}
