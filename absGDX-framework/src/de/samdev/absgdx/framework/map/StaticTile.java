package de.samdev.absgdx.framework.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A simple Tile with no update method
 */
public abstract class StaticTile extends Tile {
	public StaticTile(Texture tex) {
		super(tex);
	}

	public StaticTile(TextureRegion tex) {
		super(tex);
	}

	@Override
	public void update(float delta) {
		// NOP
	}
}
