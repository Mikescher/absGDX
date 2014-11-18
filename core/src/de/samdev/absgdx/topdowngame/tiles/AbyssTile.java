package de.samdev.absgdx.topdowngame.tiles;

import de.samdev.absgdx.Textures;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.map.Tile;

public class AbyssTile extends Tile {
	public AbyssTile() {
		super(Textures.tex_AbyssTile);
	}

	@Override
	public void update(float delta) {
		// NOP
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return true;
	}
}
