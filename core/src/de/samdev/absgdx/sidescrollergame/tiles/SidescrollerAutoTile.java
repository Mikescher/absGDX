package de.samdev.absgdx.sidescrollergame.tiles;

import java.util.HashMap;

import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.map.AutoTile;

public class SidescrollerAutoTile extends AutoTile {

	public SidescrollerAutoTile(HashMap<String, String> properties) {
		super(Textures.texsidemap, 70, 70, properties);
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return true;
	}
}
