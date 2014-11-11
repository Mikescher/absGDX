package de.samdev.absgdx.framework.entities.colliosiondetection;

public class CollisionBox extends CollisionGeometry {

	public final float width;
	public final float height;
	
	public final float outerRadius;
	
	public CollisionBox(float width, float height) {
		super();
		
		this.width = width;
		this.height = height;
		
		this.outerRadius = (float) Math.sqrt(width*width + height*height) / 2;
	}

	@Override
	public float getRadius() {
		return outerRadius;
	}

	public float getX() {
		return center.x - width/2;
	}

	public float getY() {
		return center.y - height/2;
	}
}
