package de.samdev.absgdx.framework.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * An Entity with additional physical properties (gravity, collision sliding, etc)
 *
 */
public abstract class PhysicsEntity extends Entity {
	/** The gravitational constant used in the movement calculations */
	public final static float GRAVITY_CONSTANT = 0.000001f;

	/** autom. registered in the base class accelerations-list */
	private Vector2 acc_gravity;
	
	/** This mass is used for Gravity - leave at 0.0f if you don't want Gravity */
	private float mass = 0.0f;
	
	/**
	 * Creates a new PhysicsEntity ( on position (0|0) )
	 * 
	 * @param texture the texture
	 * @param w the boundary box width
	 * @param h the boundary box height
	 */
	public PhysicsEntity(Texture texture, float w, float h) {
		super(texture, w, h);

		this.acc_gravity = addNewAcceleration();
	}
	
	/**
	 * Creates a new PhysicsEntity ( on position (0|0) )
	 * 
	 * @param texture the texture
	 * @param w the boundary box width
	 * @param h the boundary box height
	 */
	public PhysicsEntity(TextureRegion texture, float w, float h) {
		super(texture, w, h);

		this.acc_gravity = addNewAcceleration();
	}

	/**
	 * Creates a new PhysicsEntity ( on position (0|0) ) with an animation
	 * 
	 * @param textures the animation frames
	 * @param animationDuration The duration for one *full* cycle (all frames)
	 * @param w the boundary box width
	 * @param h the boundary box height
	 */
	public PhysicsEntity(TextureRegion[] textures, float animationDuration, float w, float h) {
		super(textures, animationDuration, w, h);

		this.acc_gravity = addNewAcceleration();
	}
	
	/**
	 * Change the mass of his Entity (used for Gravity calculations)
	 * 
	 * @param mass the mass
	 */
	public void setMass(float mass) {
		this.mass = mass;
		acc_gravity.y = -mass * GRAVITY_CONSTANT;
	}
	
	/**
	 * If this Entity is affected by gravity
	 * 
	 * @return true if mass != 0
	 */
	public boolean hasGravity() {
		return mass != 0f;
	}
}
