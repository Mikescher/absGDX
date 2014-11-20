package de.samdev.absgdx.framework.entities.colliosiondetection.geometries;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;

/**
 * The standard CollisionGeometryOwner for the outer MapCollisionBoxes
 */
public class OuterMapCollisionOwner implements CollisionGeometryOwner {
	@Override
	public boolean canCollideWith(CollisionGeometryOwner other) {
		return true;
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return true;
	}
}
