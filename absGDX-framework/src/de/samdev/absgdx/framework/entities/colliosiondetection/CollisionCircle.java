package de.samdev.absgdx.framework.entities.colliosiondetection;

import com.badlogic.gdx.math.Vector2;

public class CollisionCircle extends CollisionGeometry {

	public final float radius;
	
	public CollisionCircle(float radius) {
		super();
		
		this.radius = radius;
	}

	@Override
	public float getRadius() {
		return radius;
	}

}
