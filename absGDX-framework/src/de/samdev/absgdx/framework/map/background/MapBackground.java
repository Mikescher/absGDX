package de.samdev.absgdx.framework.map.background;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.samdev.absgdx.framework.map.TileMap;

/**
 * The superclass for all different Types of background
 * 
 */
public abstract class MapBackground {

	/**
	 * Render the background with the spriteBatch
	 * 
	 * @param sbatch the renderer
	 * @param map_offset the current map offset
	 * @param map the current TileMap
	 * @param visible the current visible ViewRect
	 */
	public abstract void draw(SpriteBatch sbatch, Vector2 map_offset, TileMap map, Rectangle visible);

}
