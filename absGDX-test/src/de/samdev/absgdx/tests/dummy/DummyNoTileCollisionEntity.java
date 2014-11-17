package de.samdev.absgdx.tests.dummy;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.Tile;

public class DummyNoTileCollisionEntity extends Entity {
	public static int uidctr = 1;
	
	public int UID = uidctr++;

	public int dummy_ctr_beforeUpdate = 0;
	public int dummy_ctr_onLayerAdd = 0;
	public int dummy_ctr_onActiveCollide = 0;
	public int dummy_ctr_onPassiveCollide = 0;
	public int dummy_ctr_onActiveMovementCollide = 0;
	public int dummy_ctr_onPassiveMovementCollide = 0;
	
	public boolean canCollide = true;
	public boolean canMoveCollide = true;
	
	public DummyNoTileCollisionEntity() {
		super((TextureRegion)null, 1, 1);
	}

	public DummyNoTileCollisionEntity(int zl) {
		super((TextureRegion)null, 1, 1);
		
		setZLayer(zl);
	}
	
	@Override
	public void beforeUpdate(float delta) {
		dummy_ctr_beforeUpdate++;
	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		dummy_ctr_onLayerAdd++;
	}

	@Override
	public void onActiveCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		dummy_ctr_onActiveCollide++;
	}

	@Override
	public void onPassiveCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		dummy_ctr_onPassiveCollide++;
	}

	@Override
	public void onActiveMovementCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		dummy_ctr_onActiveMovementCollide++;
	}

	@Override
	public void onPassiveMovementCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		dummy_ctr_onPassiveMovementCollide++;
	}
	
	public String dummyCtrSummary() {
		return 	dummy_ctr_beforeUpdate + "-" + 
				dummy_ctr_onLayerAdd + "-" + 
				dummy_ctr_onActiveCollide + "-" + 
				dummy_ctr_onPassiveCollide + "-" + 
				dummy_ctr_onActiveMovementCollide + "-" + 
				dummy_ctr_onPassiveMovementCollide;
	}
	
	public String dummyCtrSignSummary() {
		return 	(int)Math.signum(dummy_ctr_beforeUpdate) + "-" + 
				(int)Math.signum(dummy_ctr_onLayerAdd) + "-" + 
				(int)Math.signum(dummy_ctr_onActiveCollide) + "-" + 
				(int)Math.signum(dummy_ctr_onPassiveCollide) + "-" + 
				(int)Math.signum(dummy_ctr_onActiveMovementCollide) + "-" + 
				(int)Math.signum(dummy_ctr_onPassiveMovementCollide);
	}

	@Override
	public boolean canCollideWith(CollisionGeometryOwner other) {
		return canCollide && ! (other instanceof Tile);
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return canMoveCollide;
	}
}
