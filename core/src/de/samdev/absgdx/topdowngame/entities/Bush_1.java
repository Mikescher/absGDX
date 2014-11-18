package de.samdev.absgdx.topdowngame.entities;

import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.topdowngame.TopDownGameLayer;

public class Bush_1 extends Entity {

	public TopDownGameLayer owner;
	
	public int tick = 0;
	
	public float x, y;
	
	public Bush_1(float x, float y) {
		super(Textures.tex_Bush_full, 2, 2);
		
		this.x = x;
		this.y = y;
	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		setPosition(x, y);
	}
	
	@Override
	public void beforeUpdate(float delta) {
		//
	}

	@Override
	public void onActiveCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		System.out.println("[COLLISION ACTIVE] " + this.getClass().getSimpleName() + " -> " + passiveCollider.getClass().getSimpleName() + "(" + Integer.toHexString(myGeo.hashCode()) + " | " + Integer.toHexString(otherGeo.hashCode()) + ")");
	}

	@Override
	public void onPassiveCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		System.out.println("[COLLISION PASSIV] " + this.getClass().getSimpleName() + " -> " + activeCollider.getClass().getSimpleName() + "(" + Integer.toHexString(myGeo.hashCode()) + " | " + Integer.toHexString(otherGeo.hashCode()) + ")");
	}

	@Override
	public void onActiveMovementCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		System.out.println("[MOVE COLL ACTIVE] " + this.getClass().getSimpleName() + " -> " + passiveCollider.getClass().getSimpleName() + "(" + Integer.toHexString(myGeo.hashCode()) + " | " + Integer.toHexString(otherGeo.hashCode()) + ")");
	}

	@Override
	public void onPassiveMovementCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		System.out.println("[MOVE COLL PASSIV] " + this.getClass().getSimpleName() + " -> " + activeCollider.getClass().getSimpleName() + "(" + Integer.toHexString(myGeo.hashCode()) + " | " + Integer.toHexString(otherGeo.hashCode()) + ")");
	}

	@Override
	public boolean canCollideWith(CollisionGeometryOwner other) {
		return true;
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return true;
	}
}
