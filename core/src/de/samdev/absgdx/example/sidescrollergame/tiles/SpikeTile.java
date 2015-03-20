package de.samdev.absgdx.example.sidescrollergame.tiles;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.map.Tile;

public class SpikeTile extends Tile {

	public SpikeTile() {
		super(Textures.texSpike);
	}

	@Override
	public boolean canCollideWith(CollisionGeometryOwner other) {
		return true;
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return false;
	}

	@Override
	public void onPassiveCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		if (activeCollider instanceof Entity) {
			((Entity)activeCollider).alive = false;
		}
	}

	@Override
	public void onPassiveMovementCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		if (activeCollider instanceof Entity) {
			((Entity)activeCollider).alive = false;
		}
	}

	@Override
	public void update(float delta) {
		// NOP
	}
}
