package de.samdev.absgdx.framework.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * An Entity in the game
 *
 */
public class Entity {
	
	private float x = 0f;
	private float y = 0f;
	
	private float width;
	private float height;
	
	/**
	 * Creates a new Entity ( on position (0|0) )
	 * 
	 * @param w the boundary box width
	 * @param h teh boundary box height
	 */
	public Entity(float w, float h) {
		super();
		
		this.width = w;
		this.height = h;
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
}
