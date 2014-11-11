package de.samdev.absgdx.framework.entities.colliosiondetection;

public abstract class CollisionGeometry {

	public CollisionGeometry() {
		// TODO Auto-generated constructor stub
	}

	public abstract float getCenterX();
	public abstract float getCenterY();
	
	public abstract void setCenter(float x, float y);
	
	public abstract float getRadius();
}
