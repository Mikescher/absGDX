package de.samdev.absgdx.framework.math;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;

/**
 * This class contains mathematical methods for 2D Geometry
 *
 */
public class ShapeMath {

	/**
	 * Get the X-distance the two geometries (a circle and a rectangle) can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets CollisionGeometry.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal x distance
	 */
	public static float getXTouchDistance(CollisionCircle a, CollisionBox b) {
		if (a.getCenterY() > b.getY() && a.getCenterY() < b.getTopY())
			return (a.getRadius() + b.width/2 + CollisionGeometry.FDELTA) * Math.signum(b.getCenterX() - a.getCenterX());
		else if (a.getCenterY() <= b.getY())
			return (FloatMath.fsqrt(FloatMath.fsquare(a.getRadius() + CollisionGeometry.FDELTA) - FloatMath.fsquare(b.getY() - a.getCenterY())) + b.width/2) * Math.signum(b.getCenterX() - a.getCenterX());
		else if (a.getCenterY() >= b.getTopY())
			return (FloatMath.fsqrt(FloatMath.fsquare(a.getRadius() + CollisionGeometry.FDELTA) - FloatMath.fsquare(b.getTopY() - a.getCenterY())) + b.width/2) * Math.signum(b.getCenterX() - a.getCenterX());
		else
			return Float.NaN; // Can never happen
	}

	/**
	 * Get the Y-distance the two geometries (a circle and a rectangle) can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets CollisionGeometry.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal y distance
	 */
	public static float getYTouchDistance(CollisionCircle a, CollisionBox b) {
		if (a.getCenterX() > b.getX() && a.getCenterX() < b.getRightX())
			return (a.getRadius() + b.height/2 + CollisionGeometry.FDELTA) * Math.signum(b.getCenterY() - a.getCenterY());
		else if (a.getCenterX() <= b.getX())
			return (FloatMath.fsqrt(FloatMath.fsquare(a.getRadius() + CollisionGeometry.FDELTA) - FloatMath.fsquare(b.getX() - a.getCenterX())) + b.height/2) * Math.signum(b.getCenterY() - a.getCenterY());
		else if (a.getCenterX() >= b.getRightX())
			return (FloatMath.fsqrt(FloatMath.fsquare(a.getRadius() + CollisionGeometry.FDELTA) - FloatMath.fsquare(b.getRightX() - a.getCenterX())) + b.height/2) * Math.signum(b.getCenterY() - a.getCenterY());
		else
			return Float.NaN; // Can never happen
	}

	/**
	 * Returns if two geometries (a circle and a rectangle) intersect each other
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return true if a and b intersect each other
	 */	
	public static boolean doGeometriesIntersect(CollisionCircle a, CollisionBox b) {
		if (a.getCenterY() > b.getY() && a.getCenterY() < b.getTopY())
			return a.getCenterX() > (b.getX() - a.getRadius()) && a.getCenterX() < (b.getRightX() + a.getRadius());
		else if (a.getCenterX() > b.getX() && a.getCenterX() < b.getRightX())
			return a.getCenterY() > (b.getY() - a.getRadius()) && a.getCenterY() < (b.getTopY() + a.getRadius());
		else
			return FloatMath.fsquare(a.getCenterX()-b.getX()) + FloatMath.fsquare(a.getCenterY()-b.getY()) < FloatMath.fsquare(a.getRadius()) ||
					FloatMath.fsquare(a.getCenterX()-b.getRightX()) + FloatMath.fsquare(a.getCenterY()-b.getY()) < FloatMath.fsquare(a.getRadius()) ||
					FloatMath.fsquare(a.getCenterX()-b.getRightX()) + FloatMath.fsquare(a.getCenterY()-b.getTopY()) < FloatMath.fsquare(a.getRadius()) ||
					FloatMath.fsquare(a.getCenterX()-b.getX()) + FloatMath.fsquare(a.getCenterY()-b.getTopY()) < FloatMath.fsquare(a.getRadius());
	}
}
