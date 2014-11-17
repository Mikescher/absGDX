package de.samdev.absgdx.entities;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.DemoGameLayer;
import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;

public class Bucket_1 extends Entity {

	public DemoGameLayer owner;
	
	public Bucket_1() {
		super(Textures.tex_Bucket_full, 2, 2);
	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		setPosition(15f, 15f);
		
		this.speed.y = 5/1000f;
		
		addCollisionGeo(1, 1, new CollisionCircle(this, 1f));
	}
	
	@Override
	public void beforeUpdate(float delta) {
		this.acceleration = new Vector2(20, 20).sub(getPosition()).nor().scl(1/1000f * 1/100f);
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
		return other.getClass() != Bucket_1.class && other.getClass() != Bucket_2.class && other.getClass() != Bucket_3.class;
	}

	@Override
	public boolean canMoveCollide(CollisionGeometryOwner other) {
		return other.getClass() != Anchorpoint_1.class;
	}
}
