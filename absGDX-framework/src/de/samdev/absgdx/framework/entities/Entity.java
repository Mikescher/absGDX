package de.samdev.absgdx.framework.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryListWrapper;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionListener;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionMap;
import de.samdev.absgdx.framework.entities.colliosiondetection.ReadOnlyEntityCollisionGeometryListIterator;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionBox;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionCircle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionTriangle;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.EntityCollisionGeometry;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.math.FloatMath;
import de.samdev.absgdx.framework.math.align.AlignCorner4;

/**
 * An Entity in the game
 *
 */
public abstract class Entity implements CollisionListener, CollisionGeometryOwner {
	private final static float TOUCHING_DISTANCE = CollisionGeometry.FDELTA * 4;
	
	private final TextureRegion[] animation;
	private final int animationLength;
	private final float frameDuration;
	
	private Color colorTint = null;
	
	protected float animationPos = 0f;
	protected boolean isAnimationPaused = false;
	
	private float x = 0f;
	private float y = 0f;
	
	private float width;
	private float height;
	
	/** the owning GameLayer (NULL until added to an layer) */
	protected GameLayer owner = null;
	
	/** The (physical) acceleration forces (! plural - add new ones with addNewAcceleration() ) */
	public List<Vector2> accelerations = new ArrayList<Vector2>();
	
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
	
	/** the unique identifier of this entity */
	public final int entityID;
	private static final AtomicInteger idCounter = new AtomicInteger(1000);
	
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

		this.entityID = generateUniqueEntityID();

		this.collisionGeometriesWrapper = new CollisionGeometryListWrapper(collisionGeometries);
	}
	
	/**
	 * Creates a new Entity ( on position (0|0) )
	 * 
	 * @param texture the texture
	 * @param w the boundary box width
	 * @param h the boundary box height
	 */
	public Entity(TextureRegion texture, float w, float h) {
		super();
		
		this.width = w;
		this.height = h;
		
		this.animation = new TextureRegion[]{texture};
		this.animationLength = 1;
		this.frameDuration = 0;

		this.entityID = generateUniqueEntityID();

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

		this.entityID = generateUniqueEntityID();

		this.collisionGeometriesWrapper = new CollisionGeometryListWrapper(collisionGeometries);
	}
	
	/**
	 * Get the boundary box
	 * 
	 * @return the boundary of this entity
	 */
	public Rectangle getBoundings() {
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Get the width
	 * 
	 * @return width
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * Get the height
	 * 
	 * @return height
	 */
	public float getHeight() {
		return height;
	}
	
	/**
	 * Get the X - bottom position
	 * 
	 * @return position.bottom
	 */
	public float getPositionX() {
		return x;
	}

	/**
	 * Get the Y - left position
	 * 
	 * @return position.left
	 */
	public float getPositionY() {
		return y;
	}
	
	/**
	 * Get the X - right position
	 * 
	 * @return position.right
	 */
	public float getPositionRightX() {
		return x + width;
	}

	/**
	 * Get the Y - top position
	 * 
	 * @return position.top
	 */
	public float getPositionTopY() {
		return y + height;
	}

	/**
	 * Get the center X position
	 * 
	 * @return position.center_horz
	 */
	public float getCenterX() {
		return x + width/2;
	}


	/**
	 * Get the center Y position
	 * 
	 * @return position.center_vert
	 */
	public float getCenterY() {
		return y + height/2;
	}
	
	/**
	 * Get the position as an Vector (must create a new Vector instance)
	 * 
	 * @return position (bottom - left)
	 */
	public Vector2 getPosition() {
		return new Vector2(x, y);
	}
	
	/**
	 * Gets the middle
	 * 
	 * @return position (center)
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
				
				assert succ; //TODO Remove asserts ??
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
		boolean collided_X = movePositionX(dx);
		boolean collided_Y = movePositionY(dy);
		
		checkCollisions();
		
		return collided_X || collided_Y;
	}
	
	private boolean movePositionX(float dx) {
		if (dx == 0) return false;

		float signum = Math.signum(dx);
		float correction = -dx * 100;
		
		CollisionGeometry passiveCollider = null;
		CollisionGeometry activeCollider = null;
		
		for (EntityCollisionGeometry mygeometry : collisionGeometries) {
			mygeometry.updatePosition(getPositionX() + dx, getPositionY());			
			Set<CollisionGeometry> colliders = collisionOwner.getHardColliders(mygeometry.geometry);
			
			mygeometry.updatePosition(getPositionX(), getPositionY());
			
			float myGeo_centerX = mygeometry.geometry.getCenterX();
			mygeometry.updatePosition(getPositionX() + correction, getPositionY());
			
			for (CollisionGeometry othergeometry : colliders) {
				if (othergeometry.owner == this) continue;
				
				float new_dx = othergeometry.getCenterX() - mygeometry.geometry.getXTouchDistance(othergeometry) - myGeo_centerX;
				
				if (FloatMath.fsignum(new_dx) == -signum && dx != 0) { // reverse force
					dx = 0;
					
					passiveCollider = othergeometry;
					activeCollider = mygeometry.geometry;
				} else if (Math.abs(new_dx) <= Math.abs(dx)) {
					dx = new_dx;
					
					passiveCollider = othergeometry;
					activeCollider = mygeometry.geometry;
				}
			}
			mygeometry.updatePosition(getPositionX(), getPositionY());
		}
		
		if (Math.signum(dx) != signum) dx = 0f;
		
		this.x += dx;
		for (EntityCollisionGeometry geometry : collisionGeometries) {
			float prevX = geometry.geometry.getCenterX();
			float prevY = geometry.geometry.getCenterY();
			
			geometry.updatePosition(this.x, this.y);
			
			boolean succ = collisionOwner.moveGeometry(prevX, prevY, geometry.geometry);

			assert succ; //TODO Remove asserts ??
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
		float correction = - dy * 100;
		
		CollisionGeometry passiveCollider = null;
		CollisionGeometry activeCollider = null;
		
		for (EntityCollisionGeometry mygeometry : collisionGeometries) {
			mygeometry.updatePosition(getPositionX(), getPositionY() + dy);			
			Set<CollisionGeometry> colliders = collisionOwner.getHardColliders(mygeometry.geometry);
			
			mygeometry.updatePosition(getPositionX(), getPositionY());
			
			float myGeo_centerY = mygeometry.geometry.getCenterY();
			mygeometry.updatePosition(getPositionX(), getPositionY() + correction);
			
			for (CollisionGeometry othergeometry : colliders) {
				if (othergeometry.owner == this) continue;
				
				float new_dy = othergeometry.getCenterY() - mygeometry.geometry.getYTouchDistance(othergeometry) - myGeo_centerY;
				
				if (FloatMath.fsignum(new_dy) == -signum && dy != 0) { // reverse force
					dy = 0;
					
					passiveCollider = othergeometry;
					activeCollider = mygeometry.geometry;
				} else if (Math.abs(new_dy) <= Math.abs(dy)) {
					dy = new_dy;
					
					passiveCollider = othergeometry;
					activeCollider = mygeometry.geometry;
				}
			}
			mygeometry.updatePosition(getPositionX(), getPositionY());
		}
		
		if (Math.signum(dy) != signum) dy = 0f;
		
		this.y += dy;
		for (EntityCollisionGeometry geometry : collisionGeometries) {
			float prevX = geometry.geometry.getCenterX();
			float prevY = geometry.geometry.getCenterY();
			
			geometry.updatePosition(this.x, this.y);
			
			boolean succ = collisionOwner.moveGeometry(prevX, prevY, geometry.geometry);

			assert succ; //TODO Remove asserts ??
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
	 * Get the current X scale (of the texture).
	 * use -1 to flip the texture.
	 * 
	 * Override this method if needed
	 * 
	 * @return textureScaleX
	 */
	public float getTextureScaleX() {
		return 1;
	}
	
	/**
	 * Get the current X scale (of the texture).
	 * use -1 to flip the texture.
	 * 
	 * Override this method if needed
	 * 
	 * @return textureScaleY
	 */
	public float getTextureScaleY() {
		return 1;
	}
	
	/**
	 * Get the current clockwise rotation (of the texture) in degree.
	 * 
	 * Override this method if needed
	 * 
	 * @return textureRotation
	 */
	public float getTextureRotation() {
		return 0;
	}

	/**
	 * If the Entity is animated
	 * 
	 * @return if animationLength > 1
	 */
	public boolean isAnimated() {
		return animationLength > 1 && ! isAnimationPaused;
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
		for (Vector2 acc : accelerations) {
			speed.x += acc.x * delta;
			speed.y += acc.y * delta;
		}
		
		if (movePositionX(this.speed.x * delta)) {
			// Collision appeared
			
			speed.x = 0;
		}
		
		if (movePositionY(this.speed.y * delta)) {
			// Collision appeared
			
			speed.y = 0;
		}
		
		checkCollisions();
	}
	
	/**
	 * Get the acceleration including 
	 *  - the gravitational force
	 * 
	 * @return the sum of all accelerations
	 */
	public Vector2 getRealAcceleration() {
		Vector2 result = new Vector2();
		for (Vector2 acc : accelerations) {
			result.add(acc);
		}
		return result;
	}
	
	/**
	 * Update the Entity (called before normal update actions)
	 * 
	 * @param delta the time since the last update (in ms) - can be averaged over he last few cycles
	 */
	public abstract void beforeUpdate(float delta);
	
	/**
	 * This method is called after the Entity is added to a GameLayer
	 * 
	 * @param layer the owner
	 */
	public abstract void onLayerAdd(GameLayer layer);
	
	/**
	 * This method is called to add the Entity to a GameLayer
	 * 
	 * @param layer the owner
	 */
	public void addToLayer(GameLayer layer) {
		owner = layer;
		
		onLayerAdd(layer);
	}
	
	/**
	 * Set the Z layer
	 * Do this ONLY in the constructor - later changes will NOT be recognized
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
	 * [ATTENTION] The relative Coordinates are relative to the CENTER of the geometry
	 * Geometry are positioned via their center
	 * Entities are positioned via their bottom-left corner
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
	 * Add a collisionBox the size of this Entity
	 * 
	 * @return A wrapper object - needed to remove the geometry again
	 */
	public EntityCollisionGeometry addFullCollisionBox() {
		return addCollisionGeo(width/2, height/2, new CollisionBox(this, width, height));
	}
	
	/**
	 * Add a collisionBox the size of this Entity
	 * 
	 * @return A wrapper object - needed to remove the geometry again
	 */
	public EntityCollisionGeometry addFullCollisionCircle() {
		return addCollisionGeo(width/2, height/2, new CollisionCircle(this, FloatMath.fmin(width, height)/2f));
	}
	
	/**
	 * Add a collisionBox the size of this Entity
	 * 
	 * @param align the edge which will _not_ be included in the triangle
	 * 
	 * @return A wrapper object - needed to remove the geometry again
	 */
	public EntityCollisionGeometry addFullCollisionTriangle(AlignCorner4 align) {
		switch (align) {
		case TOPLEFT:
			return addCollisionGeo(2 * width/3, 1 * height/3, new CollisionTriangle(this, 0f, 0f,       width, 0f,     width, height));
		case TOPRIGHT:
			return addCollisionGeo(1 * width/3, 1 * height/3, new CollisionTriangle(this, 0f, 0f,       width, 0f,     0f, height));
		case BOTTOMLEFT:
			return addCollisionGeo(2 * width/3, 2 * height/3, new CollisionTriangle(this, 0f, height,   width, 0f,     width, height));
		case BOTTOMRIGHT:
			return addCollisionGeo(1 * width/3, 2 * height/3, new CollisionTriangle(this, 0f, 0f,       0f, height,    width, height));
		default:
			return null;
		}
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
	 * Returns if the entity is colliding with any other thatcan't move into each other
	 * 
	 * @return true if there is an collision
	 */
	public boolean isHardColliding() {
		return collisionOwner.isHardColliding(collisionGeometriesWrapper);
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
	
	/**
	 * Returns the first other geometry found that collides with this one so that they can't move into each other (collide hard)
	 * 
	 * @return the first colliding geometry or null
	 */
	public CollisionGeometry getFirstHardCollider() {
		return collisionOwner.getFirstHardCollider(collisionGeometriesWrapper);
	}
	
	/**
	 * Pauses / un-pauses an Animation
	 * 
	 * @param pause true means the animations will be paused
	 */
	public void pauseAnimation(boolean pause) {
		isAnimationPaused = pause;
	}
	
	/**
	 * @see #isTouchingTop()
	 * 
	 * (= "is the Entity hitting its head on the ceiling")
	 * 
	 * @return true if it is touching another geometry on the TOP side
	 */
	public boolean isTouchingTop() {
		return isTouchingNorth();
	}
	
	/**
	 * Return is one of the geometries is hard colliding (not direct - only touching) with an other geometry on the NORTH side
	 * 
	 * @return true if it is touching another geometry on the NORTH side
	 */
	public boolean isTouchingNorth() {
		for (EntityCollisionGeometry mygeometry : collisionGeometries)
			mygeometry.updatePosition(getPositionX(), getPositionY() + TOUCHING_DISTANCE);
		
		CollisionGeometry fcollider = collisionOwner.getFirstHardCollider(collisionGeometriesWrapper);

		for (EntityCollisionGeometry mygeometry : collisionGeometries)
			mygeometry.updatePosition(getPositionX(), getPositionY() - TOUCHING_DISTANCE);
		
		return fcollider != null;
	}
	
	/**
	 * @see #isTouchingEast()
	 * 
	 * @return true if it is touching another geometry on the RIGHT side
	 */
	public boolean isTouchingRight() {
		return isTouchingEast();
	}

	/**
	 * Return is one of the geometries is hard colliding (not direct - only touching) with an other geometry on the EAST side
	 * 
	 * @return true if it is touching another geometry on the EAST side
	 */
	public boolean isTouchingEast() {
		for (EntityCollisionGeometry mygeometry : collisionGeometries)
			mygeometry.updatePosition(getPositionX() + TOUCHING_DISTANCE, getPositionY());
		
		CollisionGeometry fcollider = collisionOwner.getFirstHardCollider(collisionGeometriesWrapper);

		for (EntityCollisionGeometry mygeometry : collisionGeometries)
			mygeometry.updatePosition(getPositionX() - TOUCHING_DISTANCE, getPositionY());
		
		return fcollider != null;
	}
	
	/**
	 * @see #isTouchingSouth()
	 * 
	 * (= "is the Entity standing on the ground")
	 * 
	 * @return true if it is touching another geometry on the BOTTOM side
	 */	
	public boolean isTouchingBottom() {
		return isTouchingSouth();
	}
	
	/**
	 * Return is one of the geometries is hard colliding (not direct - only touching) with an other geometry on the SOUTH side
	 * 
	 * @return true if it is touching another geometry on the SOUTH side
	 */
	public boolean isTouchingSouth() {
		for (EntityCollisionGeometry mygeometry : collisionGeometries)
			mygeometry.updatePosition(getPositionX(), getPositionY() - TOUCHING_DISTANCE);
		
		CollisionGeometry fcollider = collisionOwner.getFirstHardCollider(collisionGeometriesWrapper);

		for (EntityCollisionGeometry mygeometry : collisionGeometries)
			mygeometry.updatePosition(getPositionX(), getPositionY() + TOUCHING_DISTANCE);
		
		return fcollider != null;
	}
	
	/**
	 * @see #isTouchingWest()
	 * 
	 * @return true if it is touching another geometry on the LEFT side
	 */
	public boolean isTouchingLeft() {
		return isTouchingWest();
	}
	
	/**
	 * Return is one of the geometries is hard colliding (not direct - only touching) with an other geometry on the WEST side
	 * 
	 * @return true if it is touching another geometry on the WEST side
	 */
	public boolean isTouchingWest() {
		for (EntityCollisionGeometry mygeometry : collisionGeometries)
			mygeometry.updatePosition(getPositionX() - TOUCHING_DISTANCE, getPositionY());
		
		CollisionGeometry fcollider = collisionOwner.getFirstHardCollider(collisionGeometriesWrapper);

		for (EntityCollisionGeometry mygeometry : collisionGeometries)
			mygeometry.updatePosition(getPositionX() + TOUCHING_DISTANCE, getPositionY());
		
		return fcollider != null;
	}
	
	/**
	 * Gets a new force (= acceleration) to use with this Entity
	 * 
	 * @return the acceleration Vector2
	 */
	public Vector2 addNewAcceleration() {
		Vector2 result;
		accelerations.add(result = new Vector2());
		return result;
	}

	/**
	 * Called when the "POINTER-UP event" happens on this entity
	 * Feel free to override and use the event
	 */
	public void onPointerUp() {
		// NOP - free to override
	}

	/**
	 * Called when the "POINTER-DOWN event" happens on this entity
	 * Feel free to override and use the event
	 */
	public void onPointerDown() {
		// NOP - free to override
	}
	
	private static int generateUniqueEntityID() {
		return idCounter.getAndIncrement(); // even threadsafe
	}

	/**
	 * Return the used ColorTint (NULL means no tint used)
	 * 
	 * When rendered WHITE becomes TINT and BLACK stays BLACK
	 * The rest gets mixed
	 * 
	 * @return the OpenGL Tint Color
	 */
	public Color getColorTint() {
		return colorTint;
	}

	/**
	 * Set the used ColorTint (NULL means no tint used)
	 * 
	 * When rendered WHITE becomes TINT and BLACK stays BLACK
	 * The rest gets mixed
	 * 
	 * @param colorTint the OpenGL Tint Color
	 */
	public void setColorTint(Color colorTint) {
		this.colorTint = colorTint;
	}

	/**
	 * Called by the GameLayer to render this entity.
	 * 
	 * Is only called when this entity is visible
	 * 
	 * @param sbatch the BatchRenderer (from LibGDX)
	 * @param srenderer the ShapeRenderer (from LibGDX) - mostly used in Debug display
	 */
	public void render(SpriteBatch sbatch, ShapeRenderer srenderer) {
		Color tint = getColorTint();
		
		if (tint != null) sbatch.setColor(tint);
			
		renderTexture(sbatch, getTexture(), 0, 0);

		if (tint != null) sbatch.setColor(Color.WHITE);
	}

	/**
	 * Renders a single Texture
	 * You can use this method when you override render() to draw multiple textures for a single entity
	 * 
	 * @param sbatch the BatchRenderer (from LibGDX)
	 * @param tex the texture you want to render
	 * @param offsetX the X offset of the texture (default is zero)
	 * @param offsetY the Y offset of the texture (default is zero)
	 */
	protected void renderTexture(SpriteBatch sbatch, TextureRegion tex, float offsetX, float offsetY) {
		sbatch.draw(
				tex, 
				getPositionX() + offsetX, getPositionY() + offsetY, 
				getWidth()/2f, getHeight()/2f, 
				getWidth(), getHeight(), 
				getTextureScaleX(), getTextureScaleY(), 
				getTextureRotation());
	}
	
	/**
	 * Return true if the mouse is currently hovering over this entity
	 * 
	 * @return if mouse is over this entity
	 */
	public boolean isMouseOverEntity() {
		if (owner == null) return false;

		float mx = owner.getMouseOnMapPositionX();
		float my = owner.getMouseOnMapPositionY();
		
		return x <= mx && y <= my && x + width > mx && y + height > my;
	}
	
	/**
	 * Get the unique identifier of this entity
	 * 
	 * @return unique entity identifier
	 */
	public int getUID() {
		return entityID;
	}
	
	@Override
	public int hashCode() {
		return entityID;
	}
}
