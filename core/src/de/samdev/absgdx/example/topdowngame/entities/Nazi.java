package de.samdev.absgdx.example.topdowngame.entities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.math.align.AlignEdge4;

public class Nazi extends Entity {
	int direction = 0;
	
	GameLayer layer;
	
	Random rand = new Random();
	float ctr = 0;
	
	public Nazi() {
		super(Textures.tex_player_td[0], 500f, 2, 2.53f);
	}
	
	@Override
	public void onLayerAdd(GameLayer layer) {
		setPosition(10f, 30f);
		addFullCollisionBox();
		
		this.layer = layer;
	}

	@Override
	public void beforeUpdate(float delta) {
		speed.set(0,0);
		
		if (Gdx.input.isKeyPressed(Keys.W)) speed.y += 1;
		if (Gdx.input.isKeyPressed(Keys.A)) speed.x -= 1;
		if (Gdx.input.isKeyPressed(Keys.S)) speed.y -= 1;
		if (Gdx.input.isKeyPressed(Keys.D)) speed.x += 1;
		
		speed = speed.scl(1/speed.len()).scl(0.01f);

		if (Gdx.input.isKeyJustPressed(Keys.H)) setPositionY(getPositionY()+0.25f);
		
		isAnimationPaused = speed.isZero();
		
		if (! speed.isZero()) {
			direction = ((int)(speed.angle() + 45 + 90) % 360) / 90;
		} else animationPos = 0f;

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			switch (direction) {
			case 0:
				layer.addEntity(new BulletBill(getCenterX(), getCenterY(), AlignEdge4.BOTTOM));
				break;
			case 1:
				layer.addEntity(new BulletBill(getCenterX(), getCenterY(), AlignEdge4.RIGHT));
				break;
			case 2:
				layer.addEntity(new BulletBill(getCenterX(), getCenterY(), AlignEdge4.TOP));
				break;
			case 3:
				layer.addEntity(new BulletBill(getCenterX(), getCenterY(), AlignEdge4.LEFT));
				break;
			}
		}
		
		ctr += delta;
		
		while (ctr > 1000) {
			ctr -= 1000;
			
			Vector2 d = new Vector2(25, 0);
			d = d.rotate(rand.nextInt(360));
			
			layer.addEntity(new Chinese(getCenterX() + d.x, getCenterY() + d.y, this));
		}
	}

	@Override
	public TextureRegion getTexture() {
		if (speed.isZero())
			return Textures.tex_player_still[direction];
		else
			return Textures.tex_player_td[direction][(int)animationPos];
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
		return ! (other instanceof BulletBill);
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return other.getClass() != Bucket_1.class && other.getClass() != Bucket_2.class && other.getClass() != Bucket_3.class;
	}

}
