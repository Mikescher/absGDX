package de.samdev.absgdx.tests.dummy;

import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionMap;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.TileMap;
import de.samdev.absgdx.framework.map.mapscaleresolver.FixedMapScaleResolver;

public class DummyGameLayer extends GameLayer {

	public DummyGameLayer(int screenwidth, int screenheight) {
		super(new DummyAgdxGame(screenwidth, screenheight), TileMap.createEmptyMap(16, 16));
		
		setMapScaleResolver(new FixedMapScaleResolver(1));
	}

	public DummyGameLayer(int screenwidth, int screenheight, TileMap m) {
		super(new DummyAgdxGame(screenwidth, screenheight), m);
		
		setMapScaleResolver(new FixedMapScaleResolver(1));
	}

	public DummyGameLayer(int screenwidth, int screenheight, TileMap m, int expScale) {
		super(new DummyAgdxGame(screenwidth, screenheight), m, expScale);
		
		setMapScaleResolver(new FixedMapScaleResolver(1));
	}
	
	@Override
	public void addEntity(Entity e) {
		super.addEntity(e);
		
		super.addFutureEntities();
	}
	
	@Override
	public void onUpdate(float delta) {
		// NOP
	}

	public CollisionMap collisionMap() {
		return collisionMap;
	}
}
