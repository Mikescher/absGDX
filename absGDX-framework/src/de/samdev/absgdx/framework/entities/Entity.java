package de.samdev.absgdx.framework.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometry;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionMap;
import de.samdev.absgdx.framework.entities.colliosiondetection.EntityCollisionGeometry;
import de.samdev.absgdx.framework.entities.colliosiondetection.ReadOnlyEntityCollisionGeometryListIterator;
import de.samdev.absgdx.framework.layer.GameLayer;

/**
 * An Entity in the game
 *
 */
public abstract class Entity {
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
	
	public final List<EntityCollisionGeometry> collisionGeometries = new ArrayList<EntityCollisionGeometry>();
	
	/** 
	 *  The Z position of this entity.
	 *  Greater z indices are on top of lower indices
	 *  
	 *  If two entities have the same z position, the last-added entity is rendered on top.
	 */
	public int zlayer = 0;
	
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
	 * @param dx delta X
	 * @param dy delta Y
	 */
	public void movePosition(float dx, float dy) {
		setPosition(getPositionX() + dx, getPositionY() + dy);
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
		
		movePosition(this.speed.x * delta, this.speed.y * delta);
	}
	
	/**
	 * Update the Entity (called before normal update actions)
	 * 
	 * @param delta the time since the last update (in ms) - can be averaged over he last few cycles
	 */
	public abstract void beforeUpdate(float delta);
	
	/**
	 * Set the Z layer
	 * 
	 * @param z the z layer
	 */
	protected void setZLayer(int z) {
		this.zlayer = z;
	}
	
	public ReadOnlyEntityCollisionGeometryListIterator listCollisionGeometries() {
		return new ReadOnlyEntityCollisionGeometryListIterator(collisionGeometries);
	}
	
	public EntityCollisionGeometry addCollisionGeo(float relativeX, float relativeY, CollisionGeometry geo) {
		EntityCollisionGeometry wrapper;
		
		collisionGeometries.add(wrapper = new EntityCollisionGeometry(new Vector2(relativeX, relativeY), geo));
		
		wrapper.updatePosition(getPositionX(), getPositionY());
		
		collisionOwner.addGeometry(geo);
		
		return wrapper;
	}
	
	public abstract void onLayerAdd(GameLayer layer);
	
	public void removeAllCollisionGeos() {
		collisionGeometries.clear();
	}
	
	public void removeCollisionGeo(EntityCollisionGeometry ecg) {
		collisionGeometries.remove(ecg);
	}
}
