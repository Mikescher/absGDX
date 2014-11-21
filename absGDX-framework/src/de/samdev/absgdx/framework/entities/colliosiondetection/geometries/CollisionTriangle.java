package de.samdev.absgdx.framework.entities.colliosiondetection.geometries;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.math.FloatMath;

/**
 * A Collision Geometry in the shape of a triangle 
 *
 */
public class CollisionTriangle extends CollisionGeometry {
	
	/** The triangle point1.x (points are CounterClockWise)*/
	public final float point1_x;
	/** The triangle point1.y (points are CounterClockWise)*/
	public final float point1_y;
	/** The triangle point2.x (points are CounterClockWise)*/
	public final float point2_x;
	/** The triangle point2.y (points are CounterClockWise)*/
	public final float point2_y;
	/** The triangle point3.x (points are CounterClockWise)*/
	public final float point3_x;	
	/** The triangle point3.y (points are CounterClockWise)*/
	public final float point3_y;

	/** The circumRadius of the triangle*/
	public final float circumRadius;
	
	/** This is the x-value the initial points (in the constructor) got corrected*/
	public final float centroidCorrection_x;
	/** This is the y-value the initial points (in the constructor) got corrected*/
	public final float centroidCorrection_y;
	
	/**
	 * Creates a new Triangle
	 * 
	 * The vertices will be made relative to their centroid (!) they will use every translation they have
	 * 
	 * @param owner the Entity that owns this geometry
	 * @param p1x the first point (X)
	 * @param p1y the first point (Y) 
	 * @param p2x the second point (X) 
	 * @param p2y the second point (Y) 
	 * @param p3x the third point (X) 
	 * @param p3y the third point (Y)
	 * 
	 * @throws IllegalArgumentException if the 3 vertices lie in a line
	 */
	public CollisionTriangle(CollisionGeometryOwner owner, float p1x, float p1y, float p2x, float p2y, float p3x, float p3y) {
		super(owner);
		
		if ((p1x - p3x) * (p2y - p1y) <  (p1x - p2y) * (p3x - p1y)) { // ensure CCW
			float tmpx = p2x;
			p2x = p1x;
			p1x = tmpx;
			
			float tmpy = p2y;
			p2y = p1y;
			p1y = tmpy;
		}
		
		if ((p1x - p3x) * (p2y - p1y) ==  (p1x - p2x) * (p3y - p1y)) { // ensure real triangles
			throw new IllegalArgumentException("polygons must have an area (!= 0).");
		}
		
		this.centroidCorrection_x = (p1x+p2x+p3x) / 3f;
		this.centroidCorrection_y = (p1y+p2y+p3y) / 3f;
        		
		// Normalize around circumCenter
		p1x -= centroidCorrection_x;
		p1y -= centroidCorrection_y;
		p2x -= centroidCorrection_x;
		p2y -= centroidCorrection_y;
		p3x -= centroidCorrection_x;
		p3y -= centroidCorrection_y;
		
		this.circumRadius = FloatMath.fsqrt(FloatMath.fmax(FloatMath.fpyth(p1x, p1y), FloatMath.fpyth(p2x, p2y), FloatMath.fpyth(p3x, p3y)));
		
		this.point1_x = p1x;
		this.point1_y = p1y;
		this.point2_x = p2x;		
		this.point2_y = p2y;			
		this.point3_x = p3x;		
		this.point3_y = p3y;
	}
	
	/**
	 * Creates a new Triangle
	 * 
	 * The vertices will be made relative to their circumCenter (!) they will use every translation they have
	 * 
	 * @param owner the Entity that owns this geometry
	 * @param p1 the first point
	 * @param p2 the second point
	 * @param p3 the third point
	 * 
	 * @throws IllegalArgumentException if the 3 vertices lie in a line
	 */
	public CollisionTriangle(CollisionGeometryOwner owner, Vector2 p1, Vector2 p2, Vector2 p3) {
		this(owner, p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
	}
	
	@Override
	public float area() {
		return 0.5f * Math.abs((point1_x - point3_x) * (point2_y - point1_y) - (point1_x - point2_x) * (point3_y - point1_y));
	}

	@Override
	public float getRadius() {
		return circumRadius;
	}
	
	/**
	 * Get point1 as Vector2
	 * 
	 * @return
	 */
	public Vector2 getPoint1() {
		return new Vector2(point1_x, point1_y);
	}

	/**
	 * Get point2 as Vector2
	 * 
	 * @return
	 */
	public Vector2 getPoint2() {
		return new Vector2(point2_x, point2_y);
	}
	
	/**
	 * Get point3 as Vector2
	 * 
	 * @return
	 */
	public Vector2 getPoint3() {
		return new Vector2(point3_x, point3_y);
	}

	@Override
	public float getXTouchDistance(CollisionBox other) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public float getXTouchDistance(CollisionCircle other) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public float getXTouchDistance(CollisionTriangle other) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public float getYTouchDistance(CollisionBox other) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public float getYTouchDistance(CollisionCircle other) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public float getYTouchDistance(CollisionTriangle other) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public boolean isIntersectingWith(CollisionBox other) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public boolean isIntersectingWith(CollisionCircle other) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public boolean isIntersectingWith(CollisionTriangle other) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}
}
