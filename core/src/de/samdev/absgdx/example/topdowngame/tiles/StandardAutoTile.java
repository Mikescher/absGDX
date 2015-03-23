package de.samdev.absgdx.example.topdowngame.tiles;

import java.util.HashMap;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.map.AutoTile;

public class StandardAutoTile extends AutoTile {
	public StandardAutoTile(HashMap<String, String> properties) {
		super(Textures.texmap, 16, 16, properties);
	}

	@Override
	public void update(float delta) {
		// NOP
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return false;
	}
}
