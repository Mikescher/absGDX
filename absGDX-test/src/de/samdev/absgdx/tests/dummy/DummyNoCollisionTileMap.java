package de.samdev.absgdx.tests.dummy;

import de.samdev.absgdx.framework.map.TileMap;

public class DummyNoCollisionTileMap extends TileMap {

	public DummyNoCollisionTileMap(int width, int height) {
		super(width, height);
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				DummyTile t;
				setTile(x, y, t = new DummyTile());
				t.canCollide = false;
			}
		}
	}

}
