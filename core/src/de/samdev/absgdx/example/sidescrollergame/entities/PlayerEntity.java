package de.samdev.absgdx.example.sidescrollergame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.sidescrollergame.SidescrollerGameLayer;
import de.samdev.absgdx.example.sidescrollergame.tiles.JumpPadTile;
import de.samdev.absgdx.example.sidescrollergame.tiles.LadderTile;
import de.samdev.absgdx.example.sidescrollergame.tiles.LadderTopTile;
import de.samdev.absgdx.framework.entities.PhysicsEntity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.Tile;
import de.samdev.absgdx.framework.math.FloatMath;

public class PlayerEntity extends PhysicsEntity {

	private Vector2 movement_acc = addNewAcceleration();
	private Vector2 grinding_acc = addNewAcceleration();
	
	private boolean doCatapult = false;
	
	private SidescrollerGameLayer slayer;
	
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
		if (passiveCollider instanceof JumpPadTile && otherGeo.getCenterY() + 0.99f < myGeo.getCenterY() && ((JumpPadTile)passiveCollider).ascend <= 0) {
			doCatapult = true;
			((JumpPadTile)passiveCollider).ascend();
		}
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
		
		movement_acc.x = 0;
		if (Gdx.input.isKeyPressed(Keys.D)) movement_acc.x = +0.000004f;
		if (Gdx.input.isKeyPressed(Keys.A)) movement_acc.x = -0.000004f;
		
		movement_acc.x += Math.min(Math.max(Gdx.input.getAccelerometerY(), -4), 4) * 0.000001f;
		
		if (speed.x > 0.008 && movement_acc.x > 0) movement_acc.x = 0;
		if (speed.x < -0.008 && movement_acc.x < 0) movement_acc.x = 0;
		
		
		grinding_acc.x = 0;
		if (! Gdx.input.isKeyPressed(Keys.D) && ! Gdx.input.isKeyPressed(Keys.A)) grinding_acc.x = -0.00001f * speed.x/0.008f;
		if (! Gdx.input.isKeyPressed(Keys.D) && ! Gdx.input.isKeyPressed(Keys.A) && Math.abs(speed.x) < 0.0001) speed.x = 0;
		
		if ((Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isButtonPressed(Buttons.LEFT)) && (isTouchingBottom() || Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))) speed.y = +0.008f;
		
		Tile tile = slayer.getMap().getTile((int)getCenterX(), (int)getCenterY());
		if (tile instanceof LadderTile || tile instanceof LadderTopTile) {
			if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isButtonPressed(Buttons.LEFT)) {
				speed.y = 0.003f;
			}
		}
		
		if (doCatapult) {
			doCatapult = false;
			
			speed.y = +0.014f;
		}
	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		slayer = (SidescrollerGameLayer) layer;		
		
		addFullCollisionBox();
		
		setMass(10);
	}

	@Override
	public float getTextureScaleX() {
		return (speed.x == 0) ? 1 : FloatMath.fsignum(speed.x);
	}
}
