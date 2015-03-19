package de.samdev.absgdx.example.sidescrollergame.tiles;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.map.Tile;

public class SpikeTile extends Tile {

	public SpikeTile() {
		super(Textures.texSpike);
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return false;
	}

	@Override
	public void update(float delta) {
		// NOP
	}

}
