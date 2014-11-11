package de.samdev.absgdx.framework.entities.colliosiondetection;

import com.badlogic.gdx.math.Vector2;

/**
 * This is a CollisionGeometry linked with an Entity
 * 
 * It contains the geometry and its (relative) position
 *
 */
public class EntityCollisionGeometry {

	/** the position relative to the owning entity */
	public final Vector2 relativePosition;
	/** the CollisionGeometry */
	public final CollisionGeometry geometry;
	
	/**
	 * Creates a new EntityCollisionGeoemtry
	 * 
	 * @param relativePos the relative position
	 * @param geo the geometry
	 */
	public EntityCollisionGeometry(Vector2 relativePos, CollisionGeometry geo) {
		super();
		
		this.relativePosition = relativePos;
		this.geometry = geo;
	}

	/**
	 * Updates the position (after the entity has moved)
	 * 
	 * @param x the x position of the entity
	 * @param y the y position of the entity
	 */
	public void updatePosition(float x, float y) {
		geometry.setCenter(x + relativePosition.x, y + relativePosition.y);
	}

}
