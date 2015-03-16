package de.samdev.absgdx.framework.map.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.map.TileMap;

/**
 * This is a repeating background.
 * 
 * The texture will be repeated from the origin (0|0) to cover thw whole map
 */
public class RepeatingBackground extends MapBackground {

	private final TextureRegion texture;
	private final float scale;
	
	/**
	 * Create a new repeating Background
	 * 
	 * @param tex the background texture
	 * @param scale the scale to use for the background (bigger numbers scale down)
	 */
	public RepeatingBackground(TextureRegion tex, float scale) {
		this.texture = tex;
		this.scale = scale;
	}

	/**
	 * Create a new repeating Background
	 * 
	 * @param tex the background texture
	 * @param scale the scale to use for the background (bigger numbers scale down)
	 */
	public RepeatingBackground(Texture tex, float scale) {
		this(new TextureRegion(tex), scale);
	}

	@Override
	public void draw(SpriteBatch sbatch, Vector2 map_offset, TileMap map, Rectangle visible) {
		for (int x = (int)map_offset.x - 1; x < map.width; x+= texture.getRegionWidth() / scale) {
			for (int y = (int)map_offset.y - 1; y < map.height; y+= texture.getRegionHeight() / scale) {
				sbatch.draw(texture, x, y, texture.getRegionWidth() / scale, texture.getRegionHeight() / scale);
			}
		}
	}

}
