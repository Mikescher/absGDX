package de.samdev.absgdx.framework.entities.colliosiondetection;

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
	 * @param width the box width
	 * @param height the box height
	 */
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

	/**
	 * The BottomLeft X position
	 * 
	 * @return
	 */
	public float getX() {
		return center.x - width/2;
	}

	/**
	 * The BottomLeft Y Position
	 * 
	 * @return
	 */
	public float getY() {
		return center.y - height/2;
	}
}
