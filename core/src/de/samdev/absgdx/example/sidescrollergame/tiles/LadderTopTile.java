package de.samdev.absgdx.example.sidescrollergame.tiles;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.map.Tile;

public class LadderTopTile extends Tile {

	public LadderTopTile() {
		super(Textures.texLadderTop);
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
