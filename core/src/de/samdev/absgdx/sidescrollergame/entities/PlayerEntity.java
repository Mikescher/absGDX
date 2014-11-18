package de.samdev.absgdx.sidescrollergame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

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
		// 
	}

	@Override
	public void onPassiveCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// 
	}

	@Override
	public void onActiveMovementCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// 
	}

	@Override
	public void onPassiveMovementCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		// 
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
		pauseAnimation(speed.isZero());
		
		speed.x = 0;
		
		if (Gdx.input.isKeyPressed(Keys.D)) speed.x = +0.002f;
		if (Gdx.input.isKeyPressed(Keys.A)) speed.x = -0.002f;

		if (Gdx.input.isKeyPressed(Keys.W)) speed.y = +0.002f;
	}

	@Override
	public void onLayerAdd(GameLayer layer) {		
		addFullCollisionBox();
		
		setMass(10);
	}

}
