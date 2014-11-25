package de.samdev.absgdx.topdowngame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.math.align.AlignCorner4;

public class Anchorpoint_1 extends Entity {
	
	public Anchorpoint_1() {
		super(Textures.tex_Anchorpoint_empty, 2, 2);
	}
	
	@Override
	public void onLayerAdd(GameLayer layer) {
		setPosition(20f, 30f);
		
//		addCollisionGeo(0.65f, 1.15f, new CollisionCircle(this, 0.35f));
//		addCollisionGeo(1.35f, 1.15f, new CollisionCircle(this, 0.35f));
		
//		addCollisionGeo(1.0f, 0.85f, new CollisionBox(this, 0.8f, 1.2f));
		
		addFullCollisionTriangle(AlignCorner4.BOTTOMRIGHT);
	}

	@Override
	public void beforeUpdate(float delta) {
		speed.set(0,0);
		
		if (Gdx.input.isKeyPressed(Keys.W)) speed.y += 0.01;
		if (Gdx.input.isKeyPressed(Keys.A)) speed.x -= 0.01;
		if (Gdx.input.isKeyPressed(Keys.S)) speed.y -= 0.01;
		if (Gdx.input.isKeyPressed(Keys.D)) speed.x += 0.01;

		if (Gdx.input.isKeyJustPressed(Keys.H)) setPositionY(getPositionY()+0.25f);
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
		return other.getClass() != Bucket_1.class && other.getClass() != Bucket_2.class && other.getClass() != Bucket_3.class;
	}
}
