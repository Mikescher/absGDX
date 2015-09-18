package de.samdev.absgdx.framework.layer;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Contains a cache for the VisibleMapBox (used by GameLayer)
 * 
 * This cache returns always the same rectangle object (until it is recalculated)
 * 
 * Even though the it will invalidate itself automatically when the rectangle is manipulated,
 * it is advised_not_ to manipulate the returned instance
 */
public class VisibileMapBoxCache {

	private Rectangle cache = null;
	
	private int rectHash = -1;
	
	private Vector2 offset = null;
	private float tilesize = Float.NaN;
	private int screenWidth = -1;
	private int screenHeight = -1;

	/**
	 * Get the VisibleMapBox from the cache (recalculate cache if neccesary)
	 * 
	 * This cache returns always the same rectangle object (until it is recalculated)
	 * 
	 * Even though the it will invalidate itself automatically when the rectangle is manipulated,
	 * it is advised_not_ to manipulate the returned instance
	 * 
	 * @param map_offset the current map offset
	 * @param tilescale the current tile scale (from the current MapScaleResolver)
	 * @param screenW application Screen Width
	 * @param screenH application Screen Height
	 * @return the currently visible tiles (in tile-coordinates : 1 tile = 1 unit)
	 */
	public Rectangle getCached(Vector2 map_offset, float tilescale, int screenW, int screenH) {
		if (! isCached(map_offset, tilescale, screenW, screenH)) {
			cache = getRefreshed(map_offset, tilescale, screenW, screenH);
		}
		
		return cache;
	}
	
	/**
	 * Get the VisibleMapBox from the cache (ALWAYS recalculate the cache)
	 * 
	 * Use this method to force a cache invalidate and an immediate recalculate
	 * 
	 * This cache returns always the same rectangle object (until it is recalculated)
	 * 
	 * Even though the it will invalidate itself automatically when the rectangle is manipulated,
	 * it is advised_not_ to manipulate the returned instance
	 * 
	 * @param map_offset the current map offset
	 * @param tilescale the current tile scale (from the current MapScaleResolver)
	 * @param screenW application Screen Width
	 * @param screenH application Screen Height
	 * @return the currently visible tiles (in tile-coordinates : 1 tile = 1 unit)
	 */
	public Rectangle getRefreshed(Vector2 map_offset, float tilescale, int screenW, int screenH) {
		float tilesize = tilescale;
		
		Rectangle view = new Rectangle(map_offset.x, map_offset.y, screenW / tilesize, screenH / tilesize);
		
		rectHash = view.hashCode();
		
		return view;
	}

	/**
	 * Return if the cache is valid
	 * 
	 * @param map_offset the current map offset
	 * @param tilescale the current tile scale (from the current MapScaleResolver)
	 * @param screenW application Screen Width
	 * @param screenH application Screen Height
	 * @return true if the cache does not need to be recalculated
	 */
	public boolean isCached(Vector2 map_offset, float tilescale, int screenW, int screenH) {
		return cache != null && offset != null && offset.x == map_offset.x && offset.y == map_offset.y && tilesize == tilescale && screenWidth == screenW && screenHeight == screenH && rectHash == cache.hashCode();
	}
	
	/**
	 * Invalidate the cache
	 * 
	 * It will be recalculated on the next access
	 */
	public void invalidate() {
		cache = null;
	}
}
