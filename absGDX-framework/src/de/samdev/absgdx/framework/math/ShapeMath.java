package de.samdev.absgdx.framework.math;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometry;

public class ShapeMath {
	private final static float FDELTA = 0.00001f;
	
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
	
	public static float getXTouchDistance(CollisionCircle a, CollisionCircle b) {
		return fsqrt(fcbrt(a.getRadius() + b.getRadius()) - fcbrt(b.getCenterY() - a.getCenterY())) * Math.signum(b.getCenterX() - a.getCenterX());
	}
	
	public static float getXTouchDistance(CollisionCircle a, CollisionBox b) {
		if (a.getCenterY() > b.getY() && a.getCenterY() < b.getTopY())
			return (a.getRadius() + b.width/2 + FDELTA) * Math.signum(b.getCenterX() - a.getCenterX());
		else if (a.getCenterY() <= b.getY())
			return (fsqrt(fcbrt(a.getRadius() + FDELTA) - fcbrt(b.getY() - a.getCenterY())) + b.width/2) * Math.signum(b.getCenterX() - a.getCenterX());
		else if (a.getCenterY() >= b.getTopY())
			return (fsqrt(fcbrt(a.getRadius() + FDELTA) - fcbrt(b.getTopY() - a.getCenterY())) + b.width/2) * Math.signum(b.getCenterX() - a.getCenterX());
		else
			return Float.NaN; // Can never happen
	}
	
	public static float getXTouchDistance(CollisionBox a, CollisionBox b) {
		return (a.width/2 + b.width/2 + FDELTA) * Math.signum(b.getCenterX() - a.getCenterX());
	}
	
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
	
	public static float getYTouchDistance(CollisionCircle a, CollisionCircle b) {
		return fsqrt(fcbrt(a.getRadius() + b.getRadius() + FDELTA) - fcbrt(b.getCenterX() - a.getCenterX())) * Math.signum(b.getCenterY() - a.getCenterY());
	}
	
	public static float getYTouchDistance(CollisionCircle a, CollisionBox b) {
		if (a.getCenterX() > b.getX() && a.getCenterX() < b.getRightX())
			return (a.getRadius() + b.height/2 + FDELTA) * Math.signum(b.getCenterY() - a.getCenterY());
		else if (a.getCenterX() <= b.getX())
			return (fsqrt(fcbrt(a.getRadius() + FDELTA) - fcbrt(b.getX() - a.getCenterX())) + b.height/2) * Math.signum(b.getCenterY() - a.getCenterY());
		else if (a.getCenterX() >= b.getRightX())
			return (fsqrt(fcbrt(a.getRadius() + FDELTA) - fcbrt(b.getRightX() - a.getCenterX())) + b.height/2) * Math.signum(b.getCenterY() - a.getCenterY());
		else
			return Float.NaN; // Can never happen
	}
	
	public static float getYTouchDistance(CollisionBox a, CollisionBox b) {
		return (a.height/2 + b.height/2 + FDELTA) * Math.signum(b.getCenterY() - a.getCenterY());
	}

	public static float fcbrt(float x) {
		return x*x;
	}
	
	public static float fsqrt(float x) {
		return (float) Math.sqrt(x);
	}
	
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
	
	public static boolean doGeometriesIntersect(CollisionCircle a, CollisionCircle b) {
		return fcbrt(a.getCenterX()-b.getCenterX()) + fcbrt(a.getCenterY()-b.getCenterY()) < fcbrt(a.getRadius() + b.getRadius());
	}
	
	public static boolean doGeometriesIntersect(CollisionCircle a, CollisionBox b) {
		if (a.getCenterY() > b.getY() && a.getCenterY() < b.getTopY())
			return a.getCenterX() > (b.getX() - a.getRadius()) && a.getCenterX() < (b.getRightX() + a.getRadius());
		else if (a.getCenterX() > b.getX() && a.getCenterX() < b.getRightX())
			return a.getCenterY() > (b.getY() - a.getRadius()) && a.getCenterY() < (b.getTopY() + a.getRadius());
		else
			return fcbrt(a.getCenterX()-b.getX()) + fcbrt(a.getCenterY()-b.getY()) < fcbrt(a.getRadius()) ||
					fcbrt(a.getCenterX()-b.getRightX()) + fcbrt(a.getCenterY()-b.getY()) < fcbrt(a.getRadius()) ||
					fcbrt(a.getCenterX()-b.getRightX()) + fcbrt(a.getCenterY()-b.getTopY()) < fcbrt(a.getRadius()) ||
					fcbrt(a.getCenterX()-b.getX()) + fcbrt(a.getCenterY()-b.getTopY()) < fcbrt(a.getRadius());
	}
	
	public static boolean doGeometriesIntersect(CollisionBox a, CollisionBox b) {
		return ! (a.getRightX() < b.getX() || b.getRightX() < a.getX() || a.getTopY() < b.getY() || b.getTopY() < a.getY());
	}
}
