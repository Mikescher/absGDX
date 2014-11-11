package de.samdev.absgdx.framework.entities.colliosiondetection;

import com.badlogic.gdx.math.Vector2;

public class EntityCollisionGeometry {

	public final Vector2 relativePosition;
	public final CollisionGeometry geometry;
	
	public EntityCollisionGeometry(Vector2 relativePos, CollisionGeometry geo) {
		super();
		
		this.relativePosition = relativePos;
		this.geometry = geo;
	}

	public void updatePosition(float x, float y) {
		geometry.setCenter(x + relativePosition.x, y + relativePosition.y);
	}

}
