package de.samdev.absgdx.example.sidescrollergame.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.map.Tile;

public class TorchTile extends Tile {

	private float time = 0;
	
	public TorchTile() {
		super(Textures.texTorch1);
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return false;
	}

	@Override
	public void update(float delta) {
		time += delta;
	}

	@Override
	public TextureRegion getTexture() {
		return (((int)(time/400)%2) == 0) ? Textures.texTorch1 : Textures.texTorch2;
	}

}
