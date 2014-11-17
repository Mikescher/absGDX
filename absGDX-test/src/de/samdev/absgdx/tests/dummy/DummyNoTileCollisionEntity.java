package de.samdev.absgdx.tests.dummy;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.map.Tile;

public class DummyNoTileCollisionEntity extends DummyEntity {
	public DummyNoTileCollisionEntity() {
		super();
	}

	public DummyNoTileCollisionEntity(int zl) {
		super(zl);
		
		setZLayer(zl);
	}

	@Override
	public boolean canCollideWith(CollisionGeometryOwner other) {
		return canCollide && ! (other instanceof Tile);
	}
}
