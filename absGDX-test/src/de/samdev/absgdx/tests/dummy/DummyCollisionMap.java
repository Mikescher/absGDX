package de.samdev.absgdx.tests.dummy;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionMap;
import de.samdev.absgdx.framework.map.TileMap;

public class DummyCollisionMap extends CollisionMap {

	public DummyCollisionMap(int width, int height) {
		super(TileMap.createEmptyMap(width, height));
	}

	public DummyCollisionMap(int width, int height, int expScale) {
		super(TileMap.createEmptyMap(width, height), expScale);
	}

}
