package de.samdev.absgdx.example.sidescrollergame.tiles;

import java.util.HashMap;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.map.AutoTile;
import de.samdev.absgdx.framework.util.tiled.TmxParser;

public class SidescrollerAutoTile extends AutoTile {
	private final int gid;
	
	public SidescrollerAutoTile(HashMap<String, String> properties) {
		super(Textures.texsidemap, 70, 70, properties);

		this.gid = Integer.parseInt(properties.get(TmxParser.PROPERTY_TEXTURE_GID));
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		if (gid ==   3) return false; // Bush
		if (gid ==   4) return false; // Yellow push button
		if (gid ==   5) return false; // Cactus
		if (gid ==  94) return false; // rock
		if (gid == 150) return false; // Grass
		if (gid == 246) return false; // Arrow left
		if (gid == 329) return false; // Exit sign
		if (gid == 274) return false; // Tunnel Background
		if (gid == 213) return false; // Water Top
		if (gid == 268) return false; // Water Base
		if (gid == 180) return false; // Star
		
		return true;
	}

	@Override
	public void update(float delta) {
		// NOP
	}
}
