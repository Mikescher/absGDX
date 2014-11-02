package de.samdev.absgdx.framework.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A single Tile in a Tiled map
 *
 */
public abstract class Tile {

	private final TextureRegion texture;
	
	/**
	 * Creates a new Tile
	 * 
	 * @param region the Texture to use for this tile
	 */
	public Tile(TextureRegion region) {
		super();
		
		this.texture = region;
	}

	/**
	 * Creates a new Tile
	 * 
	 * @param tex the Texture to use for this tile
	 */
	public Tile(Texture tex) {
		super();
		
		this.texture = new TextureRegion(tex);
	}

	/**
	 * Gets the current texture - the return value can change every cycle, don't cache this
	 * 
	 * @return
	 */
	public TextureRegion getTexture() {
		return texture;
	}
	
	/**
	 * Update the Tile
	 * 
	 * @param delta the time since the last update (in ms) - can be averaged over he last few cycles
	 */
	public abstract void update(float delta);
}
