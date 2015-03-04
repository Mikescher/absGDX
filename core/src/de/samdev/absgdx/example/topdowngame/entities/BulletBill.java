package de.samdev.absgdx.example.topdowngame.entities;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.topdowngame.TopDownGameLayer;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.math.align.AlignEdge4;

public class BulletBill extends Entity {

	public TopDownGameLayer owner;
	
	public int tick = 0;
	
	public float x, y;
	
	public final AlignEdge4 direction;
	
	public BulletBill(float x, float y, AlignEdge4 direction) {
		super(Textures.tex_bulletbill, 1000, 1.45f, 0.75f);
		
		this.x = x - getWidth() / 2;
		this.y = y - getHeight() / 2;
		this.direction = direction;
		
		switch (direction) {
		case BOTTOM:
			speed.set(0, -1);
			break;
		case LEFT:
			speed.set(-1, 0);
			break;
		case RIGHT:
			speed.set(+1, 0);
			break;
		case TOP:
			speed.set(0, +1);
			break;
		}
		
		speed.scl(2);
		
		this.x += speed.x;
		this.y += speed.y;
	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		addFullCollisionBox();
		setPosition(x, y);
	}
	
	@Override
	public float getTextureRotation() {
		switch (direction) {
		case BOTTOM:
			return 90;
		case LEFT:
			return 0;
		case RIGHT:
			return 0;
		case TOP:
			return 270;
		}
		
		return 0;
	}
	
	@Override
	public float getTextureScaleX() {
		return direction == AlignEdge4.RIGHT ? 1 : -1;
	}
	
	@Override
	public void beforeUpdate(float delta) {
		switch (direction) {
		case BOTTOM:
			speed.set(0, -1);
			break;
		case LEFT:
			speed.set(-1, 0);
			break;
		case RIGHT:
			speed.set(+1, 0);
			break;
		case TOP:
			speed.set(0, +1);
			break;
		}
		
		speed.scl(1 / speed.len());
		speed.scl(0.03f);
	}

	@Override
	public void onActiveCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		if (passiveCollider instanceof Chinese) {
			((Chinese)passiveCollider).alive = false;
			alive = false;
		}
	}

	@Override
	public void onPassiveCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		if (activeCollider instanceof Chinese) {
			((Chinese)activeCollider).alive = false;
			alive = false;
		}
	}

	@Override
	public void onActiveMovementCollide(CollisionGeometryOwner passiveCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		if (passiveCollider instanceof Chinese) {
			((Chinese)passiveCollider).alive = false;
			alive = false;
		}
	}

	@Override
	public void onPassiveMovementCollide(CollisionGeometryOwner activeCollider, CollisionGeometry myGeo, CollisionGeometry otherGeo) {
		if (activeCollider instanceof Chinese) {
			((Chinese)activeCollider).alive = false;
			alive = false;
		}
	}

	@Override
	public boolean canCollideWith(CollisionGeometryOwner other) {
		return !( other instanceof Nazi);
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return false;
	}
}
