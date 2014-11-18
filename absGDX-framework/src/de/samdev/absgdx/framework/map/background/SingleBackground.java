package de.samdev.absgdx.framework.map.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.map.TileMap;

/**
 * This is a single background.
 * 
 * The texture will be rendered at (0|0) and _not_ be repeated
 *
 */
public class SingleBackground extends MapBackground {

	private final TextureRegion texture;
	private final float scale;

	/**
	 * Create a new single Background
	 * 
	 * @param tex the background texture
	 * @param scale the scale to use for the background (bigger numbers scale down)
	 */
	public SingleBackground(TextureRegion tex, float scale) {
		this.texture = tex;
		this.scale = scale;
	}

	/**
	 * Create a new single Background
	 * 
	 * @param tex the background texture
	 * @param scale the scale to use for the background (bigger numbers scale down)
	 */
	public SingleBackground(Texture tex, float scale) {
		this(new TextureRegion(tex), scale);
	}

	@Override
	public void draw(SpriteBatch sbatch, Vector2 map_offset, TileMap map, Rectangle visible) {
		sbatch.draw(texture, 0, 0, texture.getRegionWidth() / scale, texture.getRegionHeight() / scale);
	}

}
