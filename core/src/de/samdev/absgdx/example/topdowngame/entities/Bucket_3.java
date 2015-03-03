package de.samdev.absgdx.example.topdowngame.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.example.topdowngame.TopDownGameLayer;
import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;

public class Bucket_3 extends Entity {

	public TopDownGameLayer owner;
	
	private Vector2 acceleration = addNewAcceleration();

	private Entity other_A;
	private Entity other_B;
	
	public Bucket_3(Entity a, Entity b) {
		super(Textures.tex_Bucket_hay, 2, 2);
		
		other_A = a;
		other_B = b;
	}

	@Override
	public void onLayerAdd(GameLayer layer) {
		setPosition(20.0f, 15.0f);
		
		addCollisionGeo(1, 1, new CollisionCircle(this, 1f));
	}
	
	@Override
	public void beforeUpdate(float delta) {
		Vector2 other = other_A.getMiddle().add(other_A.getMiddle().sub(other_B.getMiddle()).scl(0.5f));
		acceleration.set(getMiddle().sub(other).scl(-0.0000000005f * other.len() * other.len()));
		
		for (Entity entity : owner.iterateEntities()) {
			if (entity == this) continue;
			if (!(entity instanceof Bucket_1 || entity instanceof Bucket_2)) continue;
			
			Vector2 dist = this.getMiddle().sub(entity.getMiddle());
			Vector2 dist2 = this.getMiddle().sub(entity.getMiddle()).nor();
			Vector2 dist3 = new Vector2();
			
			dist3.x = 1/dist.len() * dist2.x;
			dist3.y = 1/dist.len() * dist2.y;
			
			dist3.scl(0.15f);
			
			this.speed.add(dist3.x * dist3.x * dist3.x, dist3.y * dist3.y * dist3.y);
		}
		
		if (this.speed.len() > 0.05)
			this.speed.limit(0.05f);
		
		this.acceleration.add(speed.x*-0.0001f, speed.y*-0.0001f);
		
		Rectangle r = owner.getVisibleMapBox();
		
		if (getPositionX() < r.x || (getPositionX()+getWidth()) > (r.x + r.width)) {
			speed.x *= -1f;
			acceleration.x = 0;
		}

		if (getPositionY() < r.y || (getPositionY()+getHeight()) > (r.y + r.height)) {
			speed.y *= -1f;
			acceleration.y = 0;
		}
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
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return other.getClass() != Anchorpoint_1.class;
	}
}
