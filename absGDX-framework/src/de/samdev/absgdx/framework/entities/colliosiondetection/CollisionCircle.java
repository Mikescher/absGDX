package de.samdev.absgdx.framework.entities.colliosiondetection;

import com.badlogic.gdx.math.Vector2;

public class CollisionCircle extends CollisionGeometry {

	private Vector2 center = new Vector2();
	private final float radius;
	
	public CollisionCircle(float radius) {
		super();
		
		this.radius = radius;
	}

	@Override
	public float getCenterX() {
		return center.x;
	}

	@Override
	public float getCenterY() {
		return center.y;
	}

	@Override
	public void setCenter(float x, float y) {
		center.set(x, y);
	}

	@Override
	public float getRadius() {
		return radius;
	}

}
