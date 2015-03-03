package de.samdev.absgdx.example.sidescrollergame.tiles;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.map.Tile;

public class SpawnTile extends Tile {

	public SpawnTile() {
		super(Textures.texSpawnTile);
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return false;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

}
