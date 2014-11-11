package de.samdev.absgdx.framework.entities.colliosiondetection;


/**
 * Listens to CollisionEvents in for specific CollisionGeometries
 *
 */
public interface CollisionListener {
	/**
	 * Called when this Objects collides with an other, because the position of this one was changed
	 * 
	 * @param passiveCollider the other participant
	 * @param myGeo the geometry on this site, which collided
	 * @param otherGeo the geometry of the other side that collided
	 */
	public void onActiveCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo);
	/**
	 * Called when this Objects collides with an other, because the position of the other was changed
	 * 
	 * @param activeCollider the other participant
	 * @param myGeo the geometry on this site, which collided
	 * @param otherGeo the geometry of the other side that collided
	 */
	public void onPassiveCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo);

	/**
	 * Called when this Objects collides with an other in an movement because this object moved
	 * 
	 * The objects end beside each other (not colliding / intersecting) because its an movement
	 * 
	 * @param passiveCollider the other participant
	 * @param myGeo the geometry on this site, which collided
	 * @param otherGeo the geometry of the other side that collided
	 */
	public void onActiveMovementCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo);
	/**
	 * Called when this Objects collides with an other in an movement because the other object moved
	 * 
	 * The objects end beside each other (not colliding / intersecting) because its an movement
	 * 
	 * @param activeCollider the other participant
	 * @param myGeo the geometry on this site, which collided
	 * @param otherGeo the geometry of the other side that collided
	 */
	public void onPassiveMovementCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo);
}
