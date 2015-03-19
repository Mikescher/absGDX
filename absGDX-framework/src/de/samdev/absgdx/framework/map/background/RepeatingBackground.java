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
		float w = texture.getRegionWidth() / scale;
		float h = texture.getRegionHeight() / scale;
		
		for (int x = (int)(visible.x - 1); x < visible.x + visible.width + w; x += w) {
			for (int y = (int)(visible.y - 1); y < visible.y + visible.height + h ; y += h) {
				sbatch.draw(texture, x, y, w, h);
			}
		}
	}

}
