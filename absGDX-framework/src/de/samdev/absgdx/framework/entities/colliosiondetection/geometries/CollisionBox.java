package de.samdev.absgdx.framework.entities.colliosiondetection.geometries;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;


/**
 * A collision geometry in the shape of a rectangle
 *
 */
public class CollisionBox extends CollisionGeometry {

	/** the width of the circle */
	public final float width;
	/** the height of the circle */
	public final float height;
	
	/** the outer radius of the circle = sqrt(width*width + height*height)*/
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

	/**
	 * Creates a new CollisionBox 
	 * 
	 * @param owner the Entity that owns this geometry
	 * @param x the x position
	 * @param y the y position
	 * @param width the box width
	 * @param height the box height
	 */
	public CollisionBox(CollisionGeometryOwner owner, float x, float y, float width, float height) {
		this(owner, width, height);
		
		setCenter(x, y);
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
