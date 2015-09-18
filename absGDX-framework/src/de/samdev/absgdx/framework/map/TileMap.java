package de.samdev.absgdx.framework.map;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.reflect.ConstructorUtils;

import com.badlogic.gdx.math.Vector2;

/**
 * A tiled map
 *
 */
public class TileMap {
	/**
	 * The width of the map (in tile units)
	 */
	public final int width;
	
	/**
	 * The height of the map (in tile units)
	 */
	public final int height;

	private final Tile[][] tiles;

	/**
	 * Creates a new TileMap
	 * 
	 * @param width the map width
	 * @param height the map height
	 */
	public TileMap(int width, int height) {
		super();

		this.width = width;
		this.height = height;

		tiles = new Tile[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				setTile(x, y, new EmptyTile());
			}
		}
	}

	/**
	 * Sets a tile
	 * 
	 * @param x X position
	 * @param y Y position
	 * 
	 * @param t The tile
	 */
	public void setTile(int x, int y, Tile t) {
		tiles[x][y] = t;
	}

	/**
	 * Returns a tile
	 * 
	 * @param x the X position
	 * @param y the Y position
	 * 
	 * @return the tile
	 */
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}
	
	/**
	 * Returns a tile or NULL if OutOfBounds
	 * 
	 * @param x the X position
	 * @param y the Y position
	 * 
	 * @return the tile or NULL
	 */
	public Tile getTileChecked(int x, int y) {
		if (x >= 0 && y >= 0 && x < width && y < height)
			return tiles[x][y];
		else
			return null;
	}

	/**
	 * Gets the map width & height as an Vector2 (creates a new Vector2)
	 * 
	 * @return the map size
	 */
	public Vector2 getDimensions() {
		return new Vector2(width, height);
	}
	
	/**
	 * Update the Tile
	 * 
	 * @param delta the time since the last update (in ms) - can be averaged over he last few cycles
	 */
	public void update(float delta) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				getTile(x, y).update(delta);
			}
		}
	}
	
	/**
	 * Loads a new map filled with EmptyTile
	 * 
	 * @param w width
	 * @param h height
	 * 
	 * @return a new empty map
	 */
	public static TileMap createEmptyMap(int w, int h) {
		return new TileMap(w, h);
	}
	
	/**
	 * Loads a new map filled with an simple Tile
	 * 
	 * @param w width
	 * @param h height
	 * @param tileclass the tile to use for the map (needs an default constructor)
	 * 
	 * @return a new empty map
	 * @throws InvocationTargetException if the tile class throws an exception in its constructor
	 * @throws IllegalArgumentException if the tile class cannot be instantiated
	 * @throws IllegalAccessException if the tile class cannot be instantiated
	 * @throws InstantiationException if the tile class cannot be instantiated
	 */
	public static TileMap createEmptyMap(int w, int h, Class<? extends Tile> tileclass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TileMap t = new TileMap(w, h);
		
		Constructor<? extends Tile> tileConstructor = ConstructorUtils.getAccessibleConstructor(tileclass);
		
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				t.setTile(x, y, tileConstructor.newInstance());
			}
		}
		
		return t;
	}
	
	/**
	 * Loads a new map filled with an simple Tile
	 * (Returns NULL on error)
	 * 
	 * @param w width
	 * @param h height
	 * @param tileclass the tile to use for the map (needs an default constructor)
	 * 
	 * @return a new empty map
	 */
	public static TileMap createEmptyMapUnsafe(int w, int h, Class<? extends Tile> tileclass) {
		try {
			return createEmptyMap(w, h, tileclass);
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		} catch (InvocationTargetException e) {
			return null;
		}
	}
}
