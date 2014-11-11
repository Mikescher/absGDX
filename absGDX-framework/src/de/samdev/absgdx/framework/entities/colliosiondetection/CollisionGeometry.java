package de.samdev.absgdx.framework.entities.colliosiondetection;

import com.badlogic.gdx.math.Vector2;

public abstract class CollisionGeometry {

	protected Vector2 center = new Vector2();
	
	public CollisionGeometry() {
		// TODO Auto-generated constructor stub
	}

	public float getCenterX() {
		return center.x;
	}

	public float getCenterY() {
		return center.y;
	}

	public void setCenter(float x, float y) {
		center.set(x, y);
	}
	
	public abstract float getRadius();
}
