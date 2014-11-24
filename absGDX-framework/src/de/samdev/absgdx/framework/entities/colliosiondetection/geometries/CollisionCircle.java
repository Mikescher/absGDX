package de.samdev.absgdx.framework.entities.colliosiondetection.geometries;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.math.FloatMath;
import de.samdev.absgdx.framework.math.ShapeMath;


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
	 * @param owner the Entity that owns this geometry
	 * @param radius the radius of the circle
	 */
	public CollisionCircle(CollisionGeometryOwner owner, float radius) {
		super(owner);
		
		this.radius = radius;
	}

	@Override
	public float getRadius() {
		return radius;
	}

	@Override
	public float area() {
		return 2* FloatMath.PI * radius;
	}

	@Override
	public float getXTouchDistance(CollisionBox other) {
		return ShapeMath.getXTouchDistance(this, other);
	}

	@Override
	public float getXTouchDistance(CollisionCircle other) {
		return FloatMath.fsqrt(FloatMath.fsquare(this.getRadius() + other.getRadius() + FDELTA) - FloatMath.fsquare(other.getCenterY() - this.getCenterY())) * Math.signum(other.getCenterX() - this.getCenterX());
	}

	@Override
	public float getXTouchDistance(CollisionTriangle other) {
		return -ShapeMath.getXTouchDistance(other, this);
	}

	@Override
	public float getYTouchDistance(CollisionBox other) {
		return ShapeMath.getYTouchDistance(this, other);
	}

	@Override
	public float getYTouchDistance(CollisionCircle other) {
		return FloatMath.fsqrt(FloatMath.fsquare(this.getRadius() + other.getRadius() + FDELTA) - FloatMath.fsquare(other.getCenterX() - this.getCenterX())) * Math.signum(other.getCenterY() - this.getCenterY());
	}

	@Override
	public float getYTouchDistance(CollisionTriangle other) {
		return -ShapeMath.getYTouchDistance(other, this);
	}

	@Override
	public boolean isIntersectingWith(CollisionBox other) {
		return ShapeMath.doGeometriesIntersect(this, other);
	}

	@Override
	public boolean isIntersectingWith(CollisionCircle other) {
		return FloatMath.fsquare(this.getCenterX() - other.getCenterX()) + FloatMath.fsquare(this.getCenterY() - other.getCenterY()) < FloatMath.fsquare(this.getRadius() + other.getRadius());
	}

	@Override
	public boolean isIntersectingWith(CollisionTriangle other) {
		return ShapeMath.doGeometriesIntersect(other, this);
	}

	@Override
	public boolean containsPoint(float x, float y) {
		return FloatMath.fpyth(x - center.x, y - center.y) <= radius*radius;
	}
}
