package de.samdev.absgdx.framework.entities.colliosiondetection;

import de.samdev.absgdx.framework.entities.Entity;

/**
 * A collision geometry in the shape of a rectangle
 *
 */
public class CollisionBox extends CollisionGeometry {

	/** the width of the circle */
	public final float width;
	/** the height of the circle */
	public final float height;
	
	/** the outer radius of the circle = sqrt(width² + height²)*/
	public final float outerRadius;
	
	/**
	 * Creates a new CollisionBox 
	 * 
	 * @param owner the Entity that owns this geometry
	 * @param width the box width
	 * @param height the box height
	 */
	public CollisionBox(CollisionGeometryOwner owner, float width, float height) {
		super(owner);
		
		this.width = width;
		this.height = height;
		
		this.outerRadius = (float) Math.sqrt(width*width + height*height) / 2;
	}

	@Override
	public float getRadius() {
		return outerRadius;
	}

	/**
	 * The BottomLeft X position
	 * 
	 * @return
	 */
	public float getX() {
		return center.x - width/2;
	}
	
	/**
	 * The TopRight X position
	 * 
	 * @return
	 */
	public float getRightX() {
		return center.x + width/2;
	}

	/**
	 * The BottomLeft Y Position
	 * 
	 * @return
	 */
	public float getY() {
		return center.y - height/2;
	}
	
	/**
	 * The TopRight Y Position
	 * 
	 * @return
	 */
	public float getTopY() {
		return center.y + height/2;
	}
}
