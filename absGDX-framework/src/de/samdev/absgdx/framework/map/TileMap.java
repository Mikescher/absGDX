package de.samdev.absgdx.framework.map;


public class TileMap {
	public final int width;
	public final int height;

	private final Tile[][] tiles;

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
}
