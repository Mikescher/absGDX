package de.samdev.absgdx.example.topdowngame.entities;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;

public class Angel_1 extends Entity {
	
	public Angel_1() {
		super(Textures.tex_Angel, 6, 12);
	}
	
	@Override
	public void onLayerAdd(GameLayer layer) {
		setPosition(39.0f, 21.0f);
		
		addCollisionGeo(3.25f, 10f, new CollisionCircle(this, 1.5f)); // head
		
		addCollisionGeo(3.25f, 9f, new CollisionBox(this, 3.0f, 1f)); // shoulders
		
		addCollisionGeo(3.25f, 7f, new CollisionBox(this, 3.0f, 3f)); // torso
		
		addCollisionGeo(1.25f, 7.25f, new CollisionBox(this, 1.0f, 2.5f)); // left wing
		addCollisionGeo(5.25f, 7.25f, new CollisionBox(this, 1.0f, 2.5f)); // right wing
		
		addCollisionGeo(3.15f, 2.75f, new CollisionBox(this, 5.0f, 5.5f)); // podest
	}

	@Override
	public void beforeUpdate(float delta) {
		//
	}

	@Override
	public void onActiveCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
//		System.out.println("[COLLISION ACTIVE] " + this.getClass().getSimpleName() + " -> " + passiveCollider.getClass().getSimpleName() + "(" + Integer.toHexString(myGeo.hashCode()) + " | " + Integer.toHexString(otherGeo.hashCode()) + ")");
	}

	@Override
	public void onPassiveCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
//		System.out.println("[COLLISION PASSIV] " + this.getClass().getSimpleName() + " -> " + activeCollider.getClass().getSimpleName() + "(" + Integer.toHexString(myGeo.hashCode()) + " | " + Integer.toHexString(otherGeo.hashCode()) + ")");
	}

	@Override
	public void onActiveMovementCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
//		System.out.println("[MOVE COLL ACTIVE] " + this.getClass().getSimpleName() + " -> " + passiveCollider.getClass().getSimpleName() + "(" + Integer.toHexString(myGeo.hashCode()) + " | " + Integer.toHexString(otherGeo.hashCode()) + ")");
	}

	@Override
	public void onPassiveMovementCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
//		System.out.println("[MOVE COLL PASSIV] " + this.getClass().getSimpleName() + " -> " + activeCollider.getClass().getSimpleName() + "(" + Integer.toHexString(myGeo.hashCode()) + " | " + Integer.toHexString(otherGeo.hashCode()) + ")");
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
