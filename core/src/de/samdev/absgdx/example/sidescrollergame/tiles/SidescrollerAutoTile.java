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
		if (gid == 105) return false; // torch
		if (gid == 246) return false; // Arrow left
		if (gid == 329) return false; // Exit sign
		
		return true;
	}
}
