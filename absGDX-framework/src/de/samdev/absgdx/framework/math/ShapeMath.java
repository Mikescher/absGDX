package de.samdev.absgdx.framework.math;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;

/**
 * This class contains mathematical methods for 2D Geometry
 *
 */
public class ShapeMath {
	/** This delta value is used as the additional distance in getTouchDistance() methods */
	public final static float FDELTA = 0.00001f;
	
	/**
	 * Get the X-distance the two geometries can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal x distance
	 */
	public static float getXTouchDistance(CollisionGeometry a, CollisionGeometry b) {
		if (a instanceof CollisionCircle && b instanceof CollisionCircle) {
			return getXTouchDistance((CollisionCircle)a, (CollisionCircle)b);
		} else if (a instanceof CollisionCircle && b instanceof CollisionBox) {
			return getXTouchDistance((CollisionCircle)a, (CollisionBox)b);
		} else if (a instanceof CollisionBox && b instanceof CollisionCircle) {
			return - getXTouchDistance((CollisionCircle)b, (CollisionBox)a);
		} else if (a instanceof CollisionBox && b instanceof CollisionBox) {
			return getXTouchDistance((CollisionBox)a, (CollisionBox)b);
		} else {
			return 0f; //TODO What do ?
		}
	}
	
	/**
	 * Get the X-distance the two geometries (two circles) can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal x distance
	 */
	public static float getXTouchDistance(CollisionCircle a, CollisionCircle b) {
		return fsqrt(fsquare(a.getRadius() + b.getRadius()) - fsquare(b.getCenterY() - a.getCenterY())) * Math.signum(b.getCenterX() - a.getCenterX());
	}
	
	/**
	 * Get the X-distance the two geometries (a circle and a rectangle) can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal x distance
	 */
	public static float getXTouchDistance(CollisionCircle a, CollisionBox b) {
		if (a.getCenterY() > b.getY() && a.getCenterY() < b.getTopY())
			return (a.getRadius() + b.width/2 + FDELTA) * Math.signum(b.getCenterX() - a.getCenterX());
		else if (a.getCenterY() <= b.getY())
			return (fsqrt(fsquare(a.getRadius() + FDELTA) - fsquare(b.getY() - a.getCenterY())) + b.width/2) * Math.signum(b.getCenterX() - a.getCenterX());
		else if (a.getCenterY() >= b.getTopY())
			return (fsqrt(fsquare(a.getRadius() + FDELTA) - fsquare(b.getTopY() - a.getCenterY())) + b.width/2) * Math.signum(b.getCenterX() - a.getCenterX());
		else
			return Float.NaN; // Can never happen
	}
	
	/**
	 * Get the X-distance the two geometries (two rectangles) can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal x distance
	 */
	public static float getXTouchDistance(CollisionBox a, CollisionBox b) {
		return (a.width/2 + b.width/2 + FDELTA) * Math.signum(b.getCenterX() - a.getCenterX());
	}
	
	/**
	 * Get the Y-distance the two geometries can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal y distance
	 */
	public static float getYTouchDistance(CollisionGeometry a, CollisionGeometry b) {
		if (a instanceof CollisionCircle && b instanceof CollisionCircle) {
			return getYTouchDistance((CollisionCircle)a, (CollisionCircle)b);
		} else if (a instanceof CollisionCircle && b instanceof CollisionBox) {
			return getYTouchDistance((CollisionCircle)a, (CollisionBox)b);
		} else if (a instanceof CollisionBox && b instanceof CollisionCircle) {
			return - getYTouchDistance((CollisionCircle)b, (CollisionBox)a);
		} else if (a instanceof CollisionBox && b instanceof CollisionBox) {
			return getYTouchDistance((CollisionBox)a, (CollisionBox)b);
		} else {
			return 0f; //TODO What do ?
		}
	}
	
	/**
	 * Get the Y-distance the two geometries (two circles) can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal y distance
	 */
	public static float getYTouchDistance(CollisionCircle a, CollisionCircle b) {
		return fsqrt(fsquare(a.getRadius() + b.getRadius() + FDELTA) - fsquare(b.getCenterX() - a.getCenterX())) * Math.signum(b.getCenterY() - a.getCenterY());
	}
	
	/**
	 * Get the Y-distance the two geometries (a circle and a rectangle) can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal y distance
	 */
	public static float getYTouchDistance(CollisionCircle a, CollisionBox b) {
		if (a.getCenterX() > b.getX() && a.getCenterX() < b.getRightX())
			return (a.getRadius() + b.height/2 + FDELTA) * Math.signum(b.getCenterY() - a.getCenterY());
		else if (a.getCenterX() <= b.getX())
			return (fsqrt(fsquare(a.getRadius() + FDELTA) - fsquare(b.getX() - a.getCenterX())) + b.height/2) * Math.signum(b.getCenterY() - a.getCenterY());
		else if (a.getCenterX() >= b.getRightX())
			return (fsqrt(fsquare(a.getRadius() + FDELTA) - fsquare(b.getRightX() - a.getCenterX())) + b.height/2) * Math.signum(b.getCenterY() - a.getCenterY());
		else
			return Float.NaN; // Can never happen
	}
	
	/**
	 * Get the Y-distance the two geometries (two rectangles) can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal y distance
	 */
	public static float getYTouchDistance(CollisionBox a, CollisionBox b) {
		return (a.height/2 + b.height/2 + FDELTA) * Math.signum(b.getCenterY() - a.getCenterY());
	}

	/**
	 * Calculates the square (of a float)
	 * 
	 * @param x the input value
	 * @return x * x
	 */
	public static float fsquare(float x) {
		return x*x;
	}
	
	/**
	 * Calculates the square-root (of a float)
	 * 
	 * @param x the input value
	 * @return sqrt(x)
	 */
	public static float fsqrt(float x) {
		return (float) Math.sqrt(x);
	}
	
	/**
	 * Returns if two geometries intersect each other
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return true if a and b intersect each other
	 */
	public static boolean doGeometriesIntersect(CollisionGeometry a, CollisionGeometry b) {
		if (a instanceof CollisionCircle && b instanceof CollisionCircle) {
			return doGeometriesIntersect((CollisionCircle)a, (CollisionCircle)b);
		} else if (a instanceof CollisionCircle && b instanceof CollisionBox) {
			return doGeometriesIntersect((CollisionCircle)a, (CollisionBox)b);
		} else if (a instanceof CollisionBox && b instanceof CollisionCircle) {
			return doGeometriesIntersect((CollisionCircle)b, (CollisionBox)a);
		} else if (a instanceof CollisionBox && b instanceof CollisionBox) {
			return doGeometriesIntersect((CollisionBox)a, (CollisionBox)b);
		} else {
			return false; //TODO What do ?
		}
	}
	
	/**
	 * Returns if two geometries (two circles) intersect each other
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return true if a and b intersect each other
	 */
	public static boolean doGeometriesIntersect(CollisionCircle a, CollisionCircle b) {
		return fsquare(a.getCenterX()-b.getCenterX()) + fsquare(a.getCenterY()-b.getCenterY()) < fsquare(a.getRadius() + b.getRadius());
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
			return fsquare(a.getCenterX()-b.getX()) + fsquare(a.getCenterY()-b.getY()) < fsquare(a.getRadius()) ||
					fsquare(a.getCenterX()-b.getRightX()) + fsquare(a.getCenterY()-b.getY()) < fsquare(a.getRadius()) ||
					fsquare(a.getCenterX()-b.getRightX()) + fsquare(a.getCenterY()-b.getTopY()) < fsquare(a.getRadius()) ||
					fsquare(a.getCenterX()-b.getX()) + fsquare(a.getCenterY()-b.getTopY()) < fsquare(a.getRadius());
	}
	
	/**
	 * Returns if two geometries (two rectangles) intersect each other
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return true if a and b intersect each other
	 */
	public static boolean doGeometriesIntersect(CollisionBox a, CollisionBox b) {
		return ! (a.getRightX() < b.getX() || b.getRightX() < a.getX() || a.getTopY() < b.getY() || b.getTopY() < a.getY());
	}
}
