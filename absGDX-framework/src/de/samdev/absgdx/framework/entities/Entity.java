package de.samdev.absgdx.framework.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryListWrapper;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionListener;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionMap;
import de.samdev.absgdx.framework.entities.colliosiondetection.ReadOnlyEntityCollisionGeometryListIterator;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.EntityCollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.math.ShapeMath;

/**
 * An Entity in the game
 *
 */
public abstract class Entity implements CollisionListener, CollisionGeometryOwner {
	private final TextureRegion[] animation;
	private final int animationLength;
	private final float frameDuration;
	
	private float animationPos = 0f;
	
	private float x = 0f;
	private float y = 0f;
	
	private float width;
	private float height;
	
	/** The (physical) acceleration */
	public Vector2 acceleration = new Vector2();
	
	/** The (physical) speed */
	public Vector2 speed = new Vector2();
	
	/** If this is false the Entity will get removed at the end of the current update cycle */
	public boolean alive = true;
	
	/** 
	 * Here are the collisionBoxes of this Entity stores
	 * 
	 * **DO NOT ALTER**
	 * 
	 * Use the methods addCollisionGeo() / removeCollisionGeo() / listCollisionGeometries
	 */
	public final List<EntityCollisionGeometry> collisionGeometries = new ArrayList<EntityCollisionGeometry>();
	
	/** This is not an "real" list - its only a convenient wrapper around collisionGeometries. (do NOT try to alter this list)*/
	public final CollisionGeometryListWrapper collisionGeometriesWrapper;
	
	/** 
	 *  The Z position of this entity.
	 *  Greater z indices are on top of lower indices
	 *  
	 *  If two entities have the same z position, the last-added entity is rendered on top.
	 */
	public int zlayer = 0;
	
	/** The collision map - is set by the GameLayer when this Entity is added to it */
	public CollisionMap collisionOwner = null;
	
	/**
	 * Creates a new Entity ( on position (0|0) )
	 * 
	 * @param texture the texture
	 * @param w the boundary box width
	 * @param h the boundary box height
	 */
	public Entity(Texture texture, float w, float h) {
		super();
		
		this.width = w;
		this.height = h;
		
		this.animation = new TextureRegion[]{new TextureRegion(texture)};
		this.animationLength = 1;
		this.frameDuration = 0;
		
		this.collisionGeometriesWrapper = new CollisionGeometryListWrapper(collisionGeometries);
	}
	
	/**
	 * Creates a new Entity ( on position (0|0) )
	 * 
	 * @param textures the texture
	 * @param w the boundary box width
	 * @param h the boundary box height
	 */
	public Entity(TextureRegion textures, float w, float h) {
		super();
		
		this.width = w;
		this.height = h;
		
		this.animation = new TextureRegion[]{textures};
		this.animationLength = 1;
		this.frameDuration = 0;
		
		this.collisionGeometriesWrapper = new CollisionGeometryListWrapper(collisionGeometries);
	}

	/**
	 * Creates a new Entity ( on position (0|0) ) with an animation
	 * 
	 * @param textures the animation frames
	 * @param animationDuration The duration for one *full* cycle (all frames)
	 * @param w the boundary box width
	 * @param h the boundary box height
	 */
	public Entity(TextureRegion[] textures, float animationDuration, float w, float h) {
		super();
		
		this.width = w;
		this.height = h;
		
		this.animation = textures;
		this.animationLength = textures.length;
		this.frameDuration = animationDuration / animationLength;
		
		this.collisionGeometriesWrapper = new CollisionGeometryListWrapper(collisionGeometries);
	}
	
	/**
	 * Get the boundary box
	 * 
	 * @return
	 */
	public Rectangle getBoundings() {
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Get the width
	 * 
	 * @return
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * Get the height
	 * 
	 * @return
	 */
	public float getHeight() {
		return height;
	}
	
	/**
	 * Get the X position
	 * 
	 * @return
	 */
	public float getPositionX() {
		return x;
	}

	/**
	 * Get the Y position
	 * 
	 * @return
	 */
	public float getPositionY() {
		return y;
	}

	/**
	 * Get the center X position
	 * 
	 * @return
	 */
	public float getCenterX() {
		return x + width/2;
	}


	/**
	 * Get the center Y position
	 * 
	 * @return
	 */
	public float getCenterY() {
		return y + height/2;
	}
	
	/**
	 * Get the position as an Vector (must create a new Vector instance)
	 * 
	 * @return
	 */
	public Vector2 getPosition() {
		return new Vector2(x, y);
	}
	
	/**
	 * Gets the middle
	 * 
	 * @return the middle of the bounding box
	 */
	public Vector2 getMiddle() {
		return new Vector2(x + width/2, y + height/2);
	}
	
	/**
	 * Changes the position of this entity
	 * 
	 * @param x the x position
	 * @param y the y position
	 */
	public void setPosition(float x, float y) {
		if (this.x == x && this.y == y) return; // Performance
		
		this.x = x;
		this.y = y;

		if (collisionOwner != null) { // otherwise you couldn't set the position in the constructor
			for (EntityCollisionGeometry collgeo : collisionGeometries) {
				float prevX = collgeo.geometry.getCenterX();
				float prevY = collgeo.geometry.getCenterY();
				
				collgeo.updatePosition(x, y);
				
				boolean succ = collisionOwner.moveGeometry(prevX, prevY, collgeo.geometry);
				
				if (! succ) throw new RuntimeException("0"); //TODO REMOVE ME
			}
			
			checkCollisions();
		}
	}
	
	private void checkCollisions() {
		// Remember already collided COwners - so you don't call onPassiveCollide two times on the same object
		Set<CollisionGeometryOwner> usedPassives = new HashSet<CollisionGeometryOwner>();
		
		usedPassives.add(this); // Don't collide with geometries that I own myself
		
		for (EntityCollisionGeometry mygeometry : collisionGeometries) {
			for (CollisionGeometry othergeometry : collisionOwner.getColliders(mygeometry.geometry)) {
				if (usedPassives.add(othergeometry.owner)) {
					for (CollisionListener listener : othergeometry.listener) {
						listener.onPassiveCollide(this, othergeometry, mygeometry.geometry);
					}
					
					this.onActiveCollide(othergeometry.owner, mygeometry.geometry, othergeometry);
				}
			}
		}
	}
	
	/**
	 * Changes the position of this entity
	 * 
	 * @param pos the new position
	 */
	public void setPosition(Vector2 pos) {
		setPosition(pos.x, pos.y);
	}
	
	/**
	 * Changes the X position of this entity
	 * 
	 * @param x the x position
	 */
	public void setPositionX(float x) {
		setPosition(x, getPositionY());
	}
	
	/**
	 * Changes the Y position of this entity
	 * 
	 * @param y the y position
	 */
	public void setPositionY(float y) {
		setPosition(getPositionX(), y);
	}
	
	/**
	 * Moved the Entity by a specific delta value
	 * 
	 * This method respects collisions and does not move Entities into each other
	 * 
	 * @param dx delta X
	 * @param dy delta Y
	 * 
	 * @return true if a collision has happened
	 */
	public boolean movePosition(float dx, float dy) {
		return movePositionX(dx) | movePositionY(dy);
	}
	
	private boolean movePositionX(float dx) {
		if (dx == 0) return false;

		float signum = Math.signum(dx);
		
		CollisionGeometry passiveCollider = null;
		CollisionGeometry activeCollider = null;
		
		for (EntityCollisionGeometry mygeometry : collisionGeometries) {
			mygeometry.updatePosition(getPositionX() + dx, getPositionY());			
			Set<CollisionGeometry> colliders = collisionOwner.getColliders(mygeometry.geometry);
			mygeometry.updatePosition(getPositionX(), getPositionY());
			
			for (CollisionGeometry othergeometry : colliders) {
				if (othergeometry.owner == this) continue;
				
				float new_dx = othergeometry.getCenterX() - ShapeMath.getXTouchDistance(mygeometry.geometry, othergeometry) - mygeometry.geometry.getCenterX();
				if (Math.abs(new_dx) < Math.abs(dx))
					dx = new_dx;
				
				passiveCollider = othergeometry;
				activeCollider = mygeometry.geometry;
			}
		}
		
		if (Math.signum(dx) != signum) dx = 0f;
		
		this.x += dx;
		for (EntityCollisionGeometry geometry : collisionGeometries) {
			float prevX = geometry.geometry.getCenterX();
			float prevY = geometry.geometry.getCenterY();
			
			geometry.updatePosition(this.x, this.y);
			
			boolean succ = collisionOwner.moveGeometry(prevX, prevY, geometry.geometry);
			
			if (! succ) throw new RuntimeException("0"); //TODO REMOVE ME
		}
		
		if (passiveCollider != null) {
			for (CollisionListener listener : passiveCollider.listener) {
				listener.onPassiveMovementCollide(this, passiveCollider, activeCollider);
			}
			for (CollisionListener listener : activeCollider.listener) {
				listener.onActiveMovementCollide(passiveCollider.owner, activeCollider, passiveCollider);
			}

			return true;
		}
		
		return false;
	}
	
	private boolean movePositionY(float dy) {
		if (dy == 0) return false;
		
		float signum = Math.signum(dy);
		
		CollisionGeometry passiveCollider = null;
		CollisionGeometry activeCollider = null;
		
		for (EntityCollisionGeometry mygeometry : collisionGeometries) {
			mygeometry.updatePosition(getPositionX(), getPositionY() + dy);			
			Set<CollisionGeometry> colliders = collisionOwner.getColliders(mygeometry.geometry);
			mygeometry.updatePosition(getPositionX(), getPositionY());
			
			for (CollisionGeometry othergeometry : colliders) {
				if (othergeometry.owner == this) continue;
				
				float new_dy = othergeometry.getCenterY() - ShapeMath.getYTouchDistance(mygeometry.geometry, othergeometry) - mygeometry.geometry.getCenterY();
				if (Math.abs(new_dy) < Math.abs(dy))
					dy = new_dy;
				passiveCollider = othergeometry;
				activeCollider = mygeometry.geometry;
			}
		}
		
		if (Math.signum(dy) != signum) dy = 0f;
		
		this.y += dy;
		for (EntityCollisionGeometry geometry : collisionGeometries) {
			float prevX = geometry.geometry.getCenterX();
			float prevY = geometry.geometry.getCenterY();
			
			geometry.updatePosition(this.x, this.y);
			
			boolean succ = collisionOwner.moveGeometry(prevX, prevY, geometry.geometry);
			
			if (! succ) throw new RuntimeException("0"); //TODO REMOVE ME
		}
		
		if (passiveCollider != null) {
			for (CollisionListener listener : passiveCollider.listener) {
				listener.onPassiveMovementCollide(this, passiveCollider, activeCollider);
			}
			for (CollisionListener listener : activeCollider.listener) {
				listener.onActiveMovementCollide(passiveCollider.owner, activeCollider, passiveCollider);
			}

			return true;
		}
		
		return false;
	}
	
	/**
	 * Gets the current texture - the return value can change every cycle, don't cache this
	 * 
	 * @return the texture
	 */
	public TextureRegion getTexture() {
		return animation[(int)animationPos];
	}

	/**
	 * If the Entity is animated
	 * 
	 * @return if animationLength > 1
	 */
	public boolean isAnimated() {
		return animationLength > 1;
	}
	
	/**
	 * Update the Entity
	 * 
	 * @param delta the time since the last update (in ms) - can be averaged over he last few cycles
	 */
	public void update(float delta) {
		beforeUpdate(delta);
		
		if (isAnimated()) {
			animationPos += (delta/frameDuration);
			animationPos %= animationLength;
		}
		
		updateMovement(delta);
	}

	private void updateMovement(float delta) {
		speed.x += acceleration.x * delta;
		speed.y += acceleration.y * delta;
		
		if (movePosition(this.speed.x * delta, this.speed.y * delta)) {
			// Collision appeared
			
			speed.setZero();
			acceleration.setZero();
		}
		
	}
	
	/**
	 * Update the Entity (called before normal update actions)
	 * 
	 * @param delta the time since the last update (in ms) - can be averaged over he last few cycles
	 */
	public abstract void beforeUpdate(float delta);

	
	/**
	 * This method is called when the Entity is added to a GameLayer
	 * 
	 * @param layer the owner
	 */
	public abstract void onLayerAdd(GameLayer layer);
	
	/**
	 * Set the Z layer
	 * 
	 * @param z the z layer
	 */
	protected void setZLayer(int z) {
		this.zlayer = z;
	}
	
	/**
	 * An ListIterator to iterate through all Geometries
	 * The returned Iterator is read only (!)
	 * 
	 * @return an ListIterator for CollisionGeometriess
	 */
	public ReadOnlyEntityCollisionGeometryListIterator listCollisionGeometries() {
		return new ReadOnlyEntityCollisionGeometryListIterator(collisionGeometries);
	}
	
	/**
	 * Adds a new CollisionGeometry to this entity
	 * 
	 * @param relativeX the x position of the Geometry relative to this Entity
	 * @param relativeY the y position of the Geometry relative to this Entity
	 * @param geo the geometry
	 * @return A wrapper object - needed to remove the geometry again
	 */
	public EntityCollisionGeometry addCollisionGeo(float relativeX, float relativeY, CollisionGeometry geo) {
		geo.listener.add(this);
		
		EntityCollisionGeometry wrapper;
		collisionGeometries.add(wrapper = new EntityCollisionGeometry(new Vector2(relativeX, relativeY), geo));
		
		wrapper.updatePosition(getPositionX(), getPositionY());
		collisionOwner.addGeometry(geo);
		
		return wrapper;
	}
	
	/**
	 * Remove all Collision Geometries associated with this Entity
	 * 
	 * @return true if successful
	 */
	public boolean removeAllCollisionGeos() {
		boolean success = collisionOwner.removeGeometries(listCollisionGeometries());
		
		collisionGeometries.clear();
		
		return success;
	}
	
	/**
	 * Remove a specific with this Entity associated Geometry
	 * You get the EntityCollisionGeometry Object when adding the Geometry
	 * 
	 * @param ecg the to remove geometry
	 * @return true is successful
	 */
	public boolean removeCollisionGeo(EntityCollisionGeometry ecg) {
		if (collisionGeometries.remove(ecg)) {
			return collisionOwner.removeGeometry(ecg.geometry);
		} else {
			return false;
		}
	}
	
	/**
	 * Returns if the entity is colliding with any other
	 * 
	 * @return true if there is an collision
	 */
	public boolean isColliding() {
		return collisionOwner.isColliding(collisionGeometriesWrapper);
	}
	
	/**
	 * Returns all geometries that collide with this one
	 * 
	 * @return a Set of all colliding geometries
	 */
	public Set<CollisionGeometry> getColliders() {
		return collisionOwner.getColliders(collisionGeometriesWrapper);
	}
	
	/**
	 * Returns the first other geometry found that collides with this one
	 * 
	 * @return the first colliding geometry or null
	 */
	public CollisionGeometry getFirstCollider() {
		return collisionOwner.getFirstCollider(collisionGeometriesWrapper);
	}
}
