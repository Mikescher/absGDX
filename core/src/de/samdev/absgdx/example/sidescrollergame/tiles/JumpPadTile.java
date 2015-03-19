package de.samdev.absgdx.example.sidescrollergame.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.example.Textures;
import de.samdev.absgdx.framework.entities.colliosiondetection.CollisionGeometryOwner;
import de.samdev.absgdx.framework.map.Tile;

public class JumpPadTile extends Tile {

	public float ascend = 0;
	
	public JumpPadTile() {
		super(Textures.texJumpPad);
	}

	@Override
	public boolean canMoveCollideWith(CollisionGeometryOwner other) {
		return true;
	}

	@Override
	public TextureRegion getTexture() {
		return (ascend>0) ? Textures.texJumpPadEx : Textures.texJumpPad;
	}
	
	@Override
	public void update(float delta) {
		if (ascend > 0) ascend -= delta;
	}

	public void ascend() {
		ascend = 1500;
	}

}
