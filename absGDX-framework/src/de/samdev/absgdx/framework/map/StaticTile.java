package de.samdev.absgdx.framework.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;

/**
 * A simple Tile with no update method
 * 
 * Can be used as a base class for simple tiles
 */
public abstract class StaticTile extends Tile {
	/**
	 * Creates a new StaticTile
	 * 
	 * @param tex the tile texture
	 */
	public StaticTile(Texture tex) {
		super(tex);
	}

	/**
	 * Creates a new StaticTile
	 * 
	 * @param tex the tile texture
	 */
	public StaticTile(TextureRegion tex) {
		super(tex);
	}

	@Override
	public void update(float delta) {
		// NOP
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return false;
	}
}
