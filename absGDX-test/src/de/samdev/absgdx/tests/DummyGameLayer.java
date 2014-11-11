package de.samdev.absgdx.tests;

import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionMap;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.TileMap;

public class DummyGameLayer extends GameLayer {

	public DummyGameLayer(int screenwidth, int screenheight) {
		super(new DummyAgdxGame(screenwidth, screenheight), TileMap.createEmptyMap(16, 16));
	}

	public DummyGameLayer(int screenwidth, int screenheight, TileMap m) {
		super(new DummyAgdxGame(screenwidth, screenheight), m);
	}
	
	@Override
	public void onUpdate(float delta) {
		// NOP
	}

	public CollisionMap collisionMap() {
		return collisionMap;
	}
}
