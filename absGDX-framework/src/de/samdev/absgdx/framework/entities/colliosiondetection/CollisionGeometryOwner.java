package de.samdev.absgdx.framework.entities.colliosiondetection;


/**
 * An Object that contains 0-to-many collisionGeometries (e.g. Entity)
 *
 */
public interface CollisionGeometryOwner {
	/**
	 * Get if this object can collide with an other
	 * 
	 * [!] ATTENTION PLS:
	 * 
	 * The results of this function should be commutative (  a.canCollideWith(b) == b.canCollideWith(a)  ).
	 * If they are not canCollide(a, b) is calculated as a.canCollideWith(b) OR b.canCollideWith(a)
	 * 
	 * @param other the other CollisionGeometryOwner
	 * @return true if they can collide
	 */
	public boolean canCollideWith(CollisionGeometryOwner other);
}
