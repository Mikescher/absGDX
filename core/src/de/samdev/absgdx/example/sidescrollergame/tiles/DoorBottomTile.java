package de.samdev.absgdx.example.sidescrollergame.tiles;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.map.Tile;

public class DoorBottomTile extends Tile {

	public DoorBottomTile() {
		super(Textures.texDoorBottomTile);
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
