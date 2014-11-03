package de.samdev.absgdx.framework.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * An Entity in the game
 *
 */
public abstract class Entity {
	private final TextureRegion texture;
	
	private float x = 0f;
	private float y = 0f;
	
	private float width;
	private float height;
	
	/**
	 * Creates a new Entity ( on position (0|0) )
	 * 
	 * @param w the boundary box width
	 * @param h the boundary box height
	 * @param tex the texture
	 */
	public Entity(Texture tex, float w, float h) {
		super();
		
		this.width = w;
		this.height = h;
		
		this.texture = new TextureRegion(tex);
	}
	
	/**
	 * Creates a new Entity ( on position (0|0) )
	 * 
	 * @param w the boundary box width
	 * @param h the boundary box height
	 * @param tex the texture
	 */
	public Entity(TextureRegion tex, float w, float h) {
		super();
		
		this.width = w;
		this.height = h;
		
		this.texture = tex;
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
	 * Changes the position of this entity
	 * 
	 * @param x the x position
	 * @param y the y position
	 */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
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
	 * Gets the current texture - the return value can change every cycle, don't cache this
	 * 
	 * @return the texture
	 */
	public TextureRegion getTexture() {
		return texture;
	}
}
