package de.samdev.absgdx.example.topdowngame.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;

public class Chinese extends Entity {
	int direction = 0;
	
	GameLayer layer;
	
	final Nazi target;
	
	public Chinese(float x, float y, Nazi target) {
		super(Textures.tex_china_td[0], 500f, 2, 2.53f);
		
		this.target = target;
		
		setPosition(x, y);
	}
	
	@Override
	public void onLayerAdd(GameLayer layer) {
		addFullCollisionBox();
		
		this.layer = layer;
	}

	@Override
	public void beforeUpdate(float delta) {
		speed = new Vector2(target.getCenterX(), target.getCenterY()).sub(getCenterX(), getCenterY());
		
		speed.scl(1 / speed.len());
		speed.scl(0.005f);
		
		isAnimationPaused = speed.isZero();
		
		if (! speed.isZero()) {
			direction = ((int)(speed.angle() + 45 + 90) % 360) / 90;
		} else animationPos = 0f;
	}

	@Override
	public TextureRegion getTexture() {
		if (speed.isZero())
			return Textures.tex_china_still[direction];
		else
			return Textures.tex_china_td[direction][(int)animationPos];
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
		if (passiveCollider instanceof Nazi)
			alive = false;
	}

	@Override
	public void onPassiveMovementCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		if (activeCollider instanceof Nazi)
			alive = false;
	}

	@Override
	public boolean canCollideWith(CollisionGeometryOwner other) {
		return false;
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return other.getClass() != Bucket_1.class && other.getClass() != Bucket_2.class && other.getClass() != Bucket_3.class;
	}

}
