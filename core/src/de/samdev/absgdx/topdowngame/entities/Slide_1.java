package de.samdev.absgdx.topdowngame.entities;

import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionTriangle;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.math.align.AlignCorner4;
import de.samdev.absgdx.topdowngame.TopDownGameLayer;

public class Slide_1 extends Entity {

	public TopDownGameLayer owner;
	
	public int tick = 0;
	
	public float x, y;
	public Entity other;
	
	public CollisionGeometry t;
	
	public Slide_1(Entity e) {
		super(Textures.texSlideTile, 16, 16);
		
		this.x = 10;
		this.y = 23;
		this.other = e;
	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		setPosition(x, y);
		
		t = addFullCollisionTriangle(AlignCorner4.TOPLEFT).geometry;
//		t = addFullCollisionBox().geometry;
	}
	
	@Override
	public void beforeUpdate(float delta) {
//		System.out.println("x: " + other.collisionGeometries.get(0).geometry.getXTouchDistance(t));
//		System.out.println("y: " + other.collisionGeometries.get(0).geometry.getYTouchDistance(t));
//		System.out.println(this.getFirstHardCollider() != null);
//		System.out.println("");
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
