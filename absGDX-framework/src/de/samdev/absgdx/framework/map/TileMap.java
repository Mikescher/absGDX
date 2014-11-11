package de.samdev.absgdx.framework.map;

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
	 * @return the tile
	 */
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	/**
	 * Gets the map width & height as an Vector2 (creates a new Vector2)
	 * 
	 * @return
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
	 * @return 
	 */
	public static TileMap createEmptyMap(int w, int h) {
		return new TileMap(w, h);
	}
}
