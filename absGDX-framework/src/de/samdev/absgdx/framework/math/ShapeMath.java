package de.samdev.absgdx.framework.math;

import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.bcel.internal.generic.FLOAD;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionTriangle;

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
	 * Get the X-distance the two geometries (a triangle and a circle) can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets CollisionGeometry.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal x distance
	 */
	public static float getXTouchDistance(CollisionTriangle a, CollisionCircle b) {
		float dist_l1 = -getCircleLineXDistance(b.getCenterX(), b.getCenterY(), b.radius, a.getPoint1_X(), a.getPoint1_Y(), a.getPoint2_X(), a.getPoint2_Y());
		float dist_l2 = -getCircleLineXDistance(b.getCenterX(), b.getCenterY(), b.radius, a.getPoint2_X(), a.getPoint2_Y(), a.getPoint3_X(), a.getPoint3_Y());
		float dist_l3 = -getCircleLineXDistance(b.getCenterX(), b.getCenterY(), b.radius, a.getPoint3_X(), a.getPoint3_Y(), a.getPoint1_X(), a.getPoint1_Y());
		
		if (a.getCenter().x < b.getCenterX()) { // [+]
			return FloatMath.fmin(dist_l1, dist_l2, dist_l3);
		} else { // [-]
			return FloatMath.fmax(dist_l1, dist_l2, dist_l3);
		}
	}

	public static float getCircleLineXDistance(float c_x, float c_y, float rad, float p1_x, float p1_y, float p2_x, float p2_y) {
		float distance_p1 = (p1_x - c_x) - FloatMath.fsqrt(FloatMath.fmax(0, (rad * rad) - FloatMath.fabs(p1_y - c_y)));
		float distance_p2 = (p2_x - c_x) - FloatMath.fsqrt(FloatMath.fmax(0, (rad * rad) - FloatMath.fabs(p2_y - c_y)));
		
		float angle = (float) (Math.atan2(p2_y - p1_y, p2_x - p1_x) + Math.PI/2);
		
		float d_12_x = (p2_x - p1_x);
		float d_12_y = (p2_y - p1_y);
		
		float circle_coll_x = c_x + FloatMath.fcos(angle) * rad;
		float circle_coll_y = c_y + FloatMath.fsin(angle) * rad;
		
		float line_coll_y = circle_coll_y;
		float line_coll_s = (c_y*circle_coll_y - p1_y) / d_12_y;
		
		if (line_coll_s > 0 && (p1_y + line_coll_s * d_12_y) < p2_y) {
			float line_coll_x = p1_x + d_12_x * line_coll_s;
			
			return line_coll_x - circle_coll_x;
		} else if (line_coll_s <= 0) {
			return distance_p1;
		} else {
			return distance_p2;
		}
		
	}
	
	/**
	 * Get the X-distance the two geometries (a triangle and a box) can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets CollisionGeometry.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal x distance
	 */
	public static float getXTouchDistance(CollisionTriangle a, CollisionBox b) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
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
	 * Get the Y-distance the two geometries (a triangle and a circle) can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets CollisionGeometry.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal x distance
	 */
	public static float getYTouchDistance(CollisionTriangle a, CollisionCircle b) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	/**
	 * Get the Y-distance the two geometries (a triangle and a box) can minimally have 
	 * (at this distance they don't intersect but touch)
	 * 
	 * [!] The distance gets CollisionGeometry.FDELTA added so the no-intersection ruled is enforced
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return the minimal x distance
	 */
	public static float getYTouchDistance(CollisionTriangle a, CollisionBox b) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
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

	/**
	 * Returns if two geometries (a triangle and a circle) intersect each other
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return true if a and b intersect each other
	 */	
	public static boolean doGeometriesIntersect(CollisionTriangle a, CollisionCircle b) {
		return 
				b.containsPoint(a.getPoint1_X(), a.getPoint1_Y()) ||
				b.containsPoint(a.getPoint2_X(), a.getPoint2_Y()) ||
				b.containsPoint(a.getPoint3_X(), a.getPoint3_Y()) ||
				
				getLinePointDistanceSquared(b.getCenterX(), b.getCenterY(), a.getPoint1_X(), a.getPoint1_Y(), a.getPoint2_X(), a.getPoint2_Y()) <= (b.radius*b.radius) ||
				getLinePointDistanceSquared(b.getCenterX(), b.getCenterY(), a.getPoint2_X(), a.getPoint2_Y(), a.getPoint3_X(), a.getPoint3_Y()) <= (b.radius*b.radius) ||
				getLinePointDistanceSquared(b.getCenterX(), b.getCenterY(), a.getPoint3_X(), a.getPoint3_Y(), a.getPoint1_X(), a.getPoint1_Y()) <= (b.radius*b.radius) ||

				a.containsPoint(b.getCenterX(), b.getCenterY());
	}

	/**
	 * Returns if two geometries (a triangle and a box) intersect each other
	 * 
	 * @param a the first geometry
	 * @param b the second geometry
	 * @return true if a and b intersect each other
	 */	
	public static boolean doGeometriesIntersect(CollisionTriangle a, CollisionBox b) { // Shortcut Evaluation - The Method
		return 
				// Triangle Line 1->2
				doLinesIntersect(a.getPoint1_X(), a.getPoint1_Y(),a.getPoint2_X(), a.getPoint2_Y(), b.getX(), b.getY(), b.getX(), b.getTopY()) ||
				doLinesIntersect(a.getPoint1_X(), a.getPoint1_Y(),a.getPoint2_X(), a.getPoint2_Y(), b.getX(), b.getTopY(), b.getRightX(), b.getTopY()) ||
				doLinesIntersect(a.getPoint1_X(), a.getPoint1_Y(),a.getPoint2_X(), a.getPoint2_Y(), b.getRightX(), b.getTopY(), b.getRightX(), b.getY()) ||
				doLinesIntersect(a.getPoint1_X(), a.getPoint1_Y(),a.getPoint2_X(), a.getPoint2_Y(), b.getRightX(), b.getY(), b.getX(), b.getY()) ||

				// Triangle Line 2->3
				doLinesIntersect(a.getPoint2_X(), a.getPoint2_Y(),a.getPoint3_X(), a.getPoint3_Y(), b.getX(), b.getY(), b.getX(), b.getTopY()) ||
				doLinesIntersect(a.getPoint2_X(), a.getPoint2_Y(),a.getPoint3_X(), a.getPoint3_Y(), b.getX(), b.getTopY(), b.getRightX(), b.getTopY()) ||
				doLinesIntersect(a.getPoint2_X(), a.getPoint2_Y(),a.getPoint3_X(), a.getPoint3_Y(), b.getRightX(), b.getTopY(), b.getRightX(), b.getY()) ||
				doLinesIntersect(a.getPoint2_X(), a.getPoint2_Y(),a.getPoint3_X(), a.getPoint3_Y(), b.getRightX(), b.getY(), b.getX(), b.getY()) ||

				// Triangle Line 3->1
				doLinesIntersect(a.getPoint3_X(), a.getPoint3_Y(),a.getPoint1_X(), a.getPoint1_Y(), b.getX(), b.getY(), b.getX(), b.getTopY()) ||
				doLinesIntersect(a.getPoint3_X(), a.getPoint3_Y(),a.getPoint1_X(), a.getPoint1_Y(), b.getX(), b.getTopY(), b.getRightX(), b.getTopY()) ||
				doLinesIntersect(a.getPoint3_X(), a.getPoint3_Y(),a.getPoint1_X(), a.getPoint1_Y(), b.getRightX(), b.getTopY(), b.getRightX(), b.getY()) ||
				doLinesIntersect(a.getPoint3_X(), a.getPoint3_Y(),a.getPoint1_X(), a.getPoint1_Y(), b.getRightX(), b.getY(), b.getX(), b.getY()) ||

				// Triangle inside Box
				b.containsPoint(a.getPoint1_X(), a.getPoint2_X()) ||

				// Box inside Triangle
				a.containsPoint(b.getX(), b.getY());
	}
	
	/**
	 * Do two line segments intersect
	 * 
	 * @param l1_p1_x Point 1 of Line 1, x coordinate
	 * @param l1_p1_y Point 1 of Line 1, y coordinate
	 * @param l1_p2_x Point 2 of Line 1, x coordinate
	 * @param l1_p2_y Point 2 of Line 1, y coordinate
	 * @param l2_p1_x Point 1 of Line 2, x coordinate
	 * @param l2_p1_y Point 1 of Line 2, y coordinate
	 * @param l2_p2_x Point 2 of Line 2, x coordinate
	 * @param l2_p2_y Point 2 of Line 2, y coordinate
	 * @return true if the two lines intersect
	 */
	public static boolean doLinesIntersect(float l1_p1_x, float l1_p1_y, float l1_p2_x, float l1_p2_y, float l2_p1_x, float l2_p1_y, float l2_p2_x, float l2_p2_y) {
		float r_d = (l1_p2_x-l1_p1_x)*(l2_p2_y-l2_p1_y)-(l1_p2_y-l1_p1_y)*(l2_p2_x-l2_p1_x);
		float s_d = (l1_p2_x-l1_p1_x)*(l2_p2_y-l2_p1_y)-(l1_p2_y-l1_p1_y)*(l2_p2_x-l2_p1_x);
		
		if (r_d != 0 && s_d != 0) {
			float r_n = (l1_p1_y-l2_p1_y)*(l2_p2_x-l2_p1_x)-(l1_p1_x-l2_p1_x)*(l2_p2_y-l2_p1_y);
			float s_n = (l1_p1_y-l2_p1_y)*(l1_p2_x-l1_p1_x)-(l1_p1_x-l2_p1_x)*(l1_p2_y-l1_p1_y);
			
			float r = r_n / r_d;
			float s = s_n / s_d;
			
			return (1 >= r && r >= 0) && (1 >= s && s >= 0);
		} else {
			return false;
		}
	}
	
	/**
	 * Get the point where two line segments intersect
	 * 
	 * @param l1_p1_x Point 1 of Line 1, x coordinate
	 * @param l1_p1_y Point 1 of Line 1, y coordinate
	 * @param l1_p2_x Point 2 of Line 1, x coordinate
	 * @param l1_p2_y Point 2 of Line 1, y coordinate
	 * @param l2_p1_x Point 1 of Line 2, x coordinate
	 * @param l2_p1_y Point 1 of Line 2, y coordinate
	 * @param l2_p2_x Point 2 of Line 2, x coordinate
	 * @param l2_p2_y Point 2 of Line 2, y coordinate
	 * @return The intersection point or NULL if there isn't one
	 */
	public static Vector2 getLineIntersection(float l1_p1_x, float l1_p1_y, float l1_p2_x, float l1_p2_y, float l2_p1_x, float l2_p1_y, float l2_p2_x, float l2_p2_y) {
		float r_d = (l1_p2_x-l1_p1_x)*(l2_p2_y-l2_p1_y)-(l1_p2_y-l1_p1_y)*(l2_p2_x-l2_p1_x);
		float s_d = (l1_p2_x-l1_p1_x)*(l2_p2_y-l2_p1_y)-(l1_p2_y-l1_p1_y)*(l2_p2_x-l2_p1_x);
		
		if (r_d != 0 && s_d != 0) {
			float r_n = (l1_p1_y-l2_p1_y)*(l2_p2_x-l2_p1_x)-(l1_p1_x-l2_p1_x)*(l2_p2_y-l2_p1_y);
			float s_n = (l1_p1_y-l2_p1_y)*(l1_p2_x-l1_p1_x)-(l1_p1_x-l2_p1_x)*(l1_p2_y-l1_p1_y);
			
			float r = r_n / r_d;
			float s = s_n / s_d;
			
			if ((1 >= r && r >= 0) && (1 >= s && s >= 0)) {
				return new Vector2(l1_p1_x + r * (l1_p2_x - l1_p1_x), l1_p1_y + r * (l1_p2_y - l1_p1_y));
			}
		}
		
		return null;
	}
	
	/**
	 * Get the line - point distance
	 * 
	 * @param p_x the x coordinate of the point
	 * @param p_y the y coordinate of the point
	 * @param l1_p1_x the start point (X) of the line
	 * @param l1_p1_y the start point (Y) of the line
	 * @param l1_p2_x the end point (X) of the line
	 * @param l1_p2_y the end point (Y) of the line
	 * @return the distance line <-> point
	 */
	public static float getLinePointDistanceSquared(float p_x, float p_y, float l1_p1_x, float l1_p1_y, float l1_p2_x, float l1_p2_y) {
		float l2 = FloatMath.fpyth(l1_p2_x - l1_p1_x, l1_p2_y - l1_p1_y);

		if (l2 == 0f) {
			return FloatMath.fpyth(p_x - l1_p1_x, p_y - l1_p1_y);
		}

		float t = ((p_x - l1_p1_x) * (l1_p2_x - l1_p1_x) + (p_y - l1_p1_y) * (l1_p2_y - l1_p1_y)) / l2;

		if (t < 0f) {
			return FloatMath.fpyth(p_x - l1_p1_x, p_y - l1_p1_y);
		} else if (t > 1f) {
			return FloatMath.fpyth(p_x - l1_p2_x, p_y - l1_p2_y);
		}

		return FloatMath.fpyth(p_x - (l1_p1_x + t * (l1_p2_x - l1_p1_x)), p_y - (l1_p1_y + t * (l1_p2_y - l1_p1_y)));
	}
}
