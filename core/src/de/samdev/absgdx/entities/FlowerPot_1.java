package de.samdev.absgdx.entities;

import de.samdev.absgdx.DemoGameLayer;
import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;

public class FlowerPot_1 extends Entity {

	public DemoGameLayer owner;
	
	public float tick = 0;
	
	public int dead = 60*10 + (int)(16*60 * Math.random());
	
	public FlowerPot_1() {
		super(Textures.tex_Flowers_empty, 2, 2);		
	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		setPosition(0.0f, 00.0f + (float)(Math.random() * 20f));
		
		addCollisionGeo(1, 1, new CollisionCircle(this, 1f));
		
		if (isColliding()) alive = false;
	}
	
	@Override
	public void beforeUpdate(float delta) {
		tick += delta / 16.666f;
		
		if (getPositionY() < 5) {
			acceleration.y =  0.00003f;
		} else {
			acceleration.y = -0.00003f;
		}
		
		speed.x = 0.0025f;
		
		speed.clamp(0.0f, 0.015f);
		
		if (tick > dead)
			this.alive = false;
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
		return other.getClass() != FlowerPot_1.class;
	}
}
