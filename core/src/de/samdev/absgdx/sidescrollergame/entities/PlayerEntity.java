package de.samdev.absgdx.sidescrollergame.entities;

import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;

public class PlayerEntity extends Entity {

	public PlayerEntity(float x, float y) {
		super(Textures.tex_player, 1000, 1, 92/70f);

		setPosition(x, y);
	}

	@Override
	public void onActiveCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPassiveCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActiveMovementCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPassiveMovementCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// TODO Auto-generated method stub

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
	public void beforeUpdate(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		// TODO Auto-generated method stub

	}

}
