package de.samdev.absgdx.example.testlayer;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.AgdxGame;
import de.samdev.absgdx.framework.layer.GameLayer;
import de.samdev.absgdx.framework.map.TileMap;

public class TestLayer extends GameLayer {

	public TestLayer(AgdxGame owner) {
		super(owner, createMap());
	}

	private static TileMap createMap() {
		TileMap tm = new TileMap(0XA, 0xA);
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				tm.setTile(x, y, new TestTile(Textures.tex_chess_tiles[(x%2) ^ (y%2)]));
			}
		}
		
		return tm;
	}

	boolean notfirst= false;
	@Override
	public void onUpdate(float delta) {
		if (notfirst) return;
		notfirst = true;
		
		for (int i = 0; i < 300; i++) {
			double time = System.currentTimeMillis();
			
			for (int j = 0; j < i*100; j++) addEntity(new TestEntity(i/110f, i/110f));
			addFutureEntities();
			entities.testIntegrity();
			
			time = System.currentTimeMillis() - time;
			
			System.out.println(getEntityCount() + ", " + time);
			
			entities.clear();
		}
	}

}
