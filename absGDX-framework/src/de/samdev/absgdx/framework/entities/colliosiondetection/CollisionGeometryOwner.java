package de.samdev.absgdx.framework.entities.colliosiondetection;

/**
 * An Object that contains 0-to-many collisionGeometries (e.g. Entity)
 *
 */
public interface CollisionGeometryOwner {
	/**
	 * Get if this object can collide with an other
	 * 
	 * return FALSE: This Entity has no collisions - not even collision notices
	 * return TRUE : This Entity can either hard or soft collide - depending on canMoveCollide()
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

	/**
	 * Get if this object can not move into another.
	 * This is used in the Entity-Method movePosition()
	 * 
	 * return FALSE: This Entity can move over the other entity 
	 * => SOFT COLLISION (Only notice)
	 * 
	 * return TRUE : This Entity can not move over the other entity - they will collide.
	 * => HARD COLLISION (can't move into each other with movePosition() )
	 * 
	 * Normally if a is the moving part the method is called as a.canMoveCollide(b).
	 * Except when b is a Tile - the it is always the Tile method that is called as in b.canCollideWith(a).
	 * 
	 * @param other the tile to collide
	 * @return true if they can collide
	 */
	public boolean canMoveCollideWith(CollisionGeometryOwner other);
}
