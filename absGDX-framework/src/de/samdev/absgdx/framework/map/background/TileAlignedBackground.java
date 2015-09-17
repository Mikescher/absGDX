package de.samdev.absgdx.framework.map.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.background.MapBackground;

/**
 * This is a repeating background.
 * 
 * The texture will be repeated from the map-origin (0|0) to cover the whole map
 * The texture tiles are aligned to the map tiles, with scale=1 the texture-tiles equal the map-tiles
 * But the texture-tiles go on after the map borders and fill the whole viewport
 */
public class TileAlignedBackground extends MapBackground {

	private final TextureRegion texture;
	private final float scale;

	/**
	 * Create a new repeating Background aligned to the TiledMap
	 * 
	 * @param tex the background texture
	 * @param scale size of a background tile (1 = Background equals MapTile) (2 = Background equals 4 MapTile) ...
	 */
	public TileAlignedBackground(TextureRegion tex, float scale) {
		this.texture = tex;
		this.scale = scale;
	}

	/**
	 * Create a new repeating Background aligned to the TiledMap
	 * 
	 * @param tex the background texture
	 * @param scale size of a background tile (1 = Background equals MapTile) (2 = Background equals 4 MapTile) ...
	 */
	public TileAlignedBackground(Texture tex, float scale) {
		this(new TextureRegion(tex), scale);
	}

	@Override
	public void draw(SpriteBatch sbatch, Vector2 map_offset, TileMap map, Rectangle visible) {
		int minX = (int)(visible.x + map_offset.x);
		int minY = (int)(visible.y + map_offset.y);
		int maxX = (int)(visible.x + visible.width - map_offset.x + 1);
		int maxY = (int)(visible.y + visible.height - map_offset.y + 1);
		
		for (int x = minX; x < maxX; x+=scale) {
			for (int y = minY; y < maxY; y+=scale) {
				sbatch.draw(texture, x  , y, scale, scale);
			}
		}
	}

}
