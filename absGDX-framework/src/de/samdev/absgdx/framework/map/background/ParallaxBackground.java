package de.samdev.absgdx.framework.map.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.map.TileMap;

/**
 * This is a parallax scrolling background.
 * 
 * The given Texture should be smaller than the map.
 * The texture will then move slower than the actual map to provide a parallax effect
 *
 */
public class ParallaxBackground extends MapBackground {

	private final TextureRegion texture;
	private final float scale;
	
	/**
	 * Create a new parallax Background
	 * 
	 * @param tex the background texture
	 * @param scale the scale to use for the background (bigger numbers scale down)
	 */
	public ParallaxBackground(TextureRegion tex, float scale) {
		this.texture = tex;
		this.scale = scale;
	}
	
	/**
	 * Create a new parallax Background
	 * 
	 * @param tex the background texture
	 * @param scale the scale to use for the background (bigger numbers scale down)
	 */
	public ParallaxBackground(Texture tex, float scale) {
		this(new TextureRegion(tex), scale);
	}

	@Override
	public void draw(SpriteBatch sbatch, Vector2 map_offset, TileMap map, Rectangle visible) {
		float percentage_x = map_offset.x / (map.width - visible.width * 1f);
		float maximum_x = (map.width - (texture.getRegionWidth() / scale));
		float pos_x = percentage_x * maximum_x;
		
		float percentage_y = map_offset.y / (map.height - visible.height * 1f);
		float maximum_y = (map.height - (texture.getRegionHeight() / scale));
		float pos_y = percentage_y * maximum_y;
		
		sbatch.draw(texture, pos_x, pos_y, texture.getRegionWidth() / scale, texture.getRegionHeight() / scale);
	}

}
