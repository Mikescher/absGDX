package de.samdev.absgdx.framework.entities.colliosiondetection;

/**
 * A Collision Geometry in the shape of a circle
 *
 */
public class CollisionCircle extends CollisionGeometry {

	/** the radius of the circle */
	public final float radius;
	
	/**
	 * Creates a new CollisionCircle
	 * 
	 * @param radius the radius of the circle
	 */
	public CollisionCircle(float radius) {
		super();
		
		this.radius = radius;
	}

	@Override
	public float getRadius() {
		return radius;
	}

}
