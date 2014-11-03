package de.samdev.absgdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.samdev.absgdx.framework.entities.Entity;

public class Player extends Entity {

	public Player() {
		super(new TextureRegion[]{Textures.tex_Bucket_full, Textures.tex_Bucket_empty, Textures.tex_Bucket_hay}, 1000, 2, 2);
		
		setPosition(0.5f, 0.5f);
	}

	@Override
	public void beforeUpdate(float delta) {
		// TODO Auto-generated method stub
		
	}

}
