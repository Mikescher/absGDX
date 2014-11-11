package de.samdev.absgdx.tests.dummy;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometry;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.layer.GameLayer;

public class DummyEntity extends Entity {
	public static int uidctr = 1;
	
	public int UID = uidctr++;
	
	public DummyEntity() {
		super((TextureRegion)null, 1, 1);
	}

	public DummyEntity(int zl) {
		super((TextureRegion)null, 1, 1);
		
		setZLayer(zl);
	}
	
	@Override
	public void beforeUpdate(float delta) {
		// NOP
	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		// NOP
	}

	@Override
	public void onActiveCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// NOP
	}

	@Override
	public void onPassiveCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// NOP
	}
}
