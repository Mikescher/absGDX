package de.samdev.absgdx;

import de.samdev.absgdx.framework.entities.Entity;

public class Player extends Entity {

	public Player() {
		super(Textures.tex_Bucket_full, 2, 2);
		
		setPosition(0.5f, 0.5f);
	}

}
